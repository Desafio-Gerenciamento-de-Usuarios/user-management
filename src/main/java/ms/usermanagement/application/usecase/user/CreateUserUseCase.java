package ms.usermanagement.application.usecase.user;

import ms.usermanagement.application.facades.MessageFacade;
import ms.usermanagement.application.messages.NotifyUserMessage;
import ms.usermanagement.application.usecase.VoidUseCase;
import ms.usermanagement.application.usecase.user.Input.CreateUserInput;
import ms.usermanagement.domain.gateway.UserGateway;
import ms.usermanagement.domain.model.Address;
import ms.usermanagement.domain.model.User;
import ms.usermanagement.infrastructure.config.ServerInfo;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class CreateUserUseCase extends VoidUseCase<CreateUserInput> {

    private final UserGateway userGateway;
    private final MessageFacade messageFacade;
    private final TemplateEngine templateEngine;
    private final ServerInfo serverInfo;

    public CreateUserUseCase(UserGateway userGateway, MessageFacade messageFacade, TemplateEngine templateEngine, ServerInfo serverInfo) {
        this.userGateway = userGateway;
        this.messageFacade = messageFacade;
        this.templateEngine = templateEngine;
        this.serverInfo = serverInfo;
    }

    @Override
    public void execute(CreateUserInput input) {

        try {
            validate(input);

            final User domain = User.create(
                    input.id(),
                    input.document(),
                    input.email(),
                    input.name(),
                    input.phone(),
                    input.addresses().stream()
                            .map(a -> Address.create(a.read(), a.number(), a.city(), a.state(), a.zipCode()))
                            .toList()
            );

            userGateway.save(domain);

            messageFacade.sendMessage(convertToMessage(domain));

        } catch (IllegalArgumentException e) {
            messageFacade.sendMessage(convertToErrorMessage(input, e.getMessage()));
            throw e;
        } catch (Exception e) {
            messageFacade.sendMessage(convertToErrorMessage(input, "Erro inesperado: " + e.getMessage()));
            throw e;
        }
    }

    @Override
    public void validate(CreateUserInput input) {
        if (userGateway.existsByEmail(input.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (userGateway.existByDocument(input.document())) {
            throw new IllegalArgumentException("Document already exists");
        }
    }

    private String convertToTemplate(String templateName, Map<String, Object> variables) {
        final Context context = new Context();
        variables.forEach(context::setVariable);
        return templateEngine.process(templateName, context);
    }

    private NotifyUserMessage convertToMessage(User user) {
        Map<String, Object> variables = Map.of(
                "nameUser", user.getName(),
                "collectionInsomnia", serverInfo.getBaseUrl().concat("/collections/insomnia"),
                "collectionPostman", serverInfo.getBaseUrl().concat("/collections/postman"),
                "dateCreate", user.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                )
        );

        final String body = convertToTemplate("email-create-user", variables);

        return new NotifyUserMessage(user.getEmail(), "Usuário Criado", body);
    }

    private NotifyUserMessage convertToErrorMessage(CreateUserInput input, String errorMessage) {
        Map<String, Object> variables = Map.of(
                "nameUser", input.name(),
                "dateAttempt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                "errorMessage", errorMessage
        );

        final String body = convertToTemplate("email-error-user", variables);

        return new NotifyUserMessage(input.email(), "Problema na Criação do Usuário", body);
    }
}
