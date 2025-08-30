package ms.usermanagement.infrastructure.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ms.usermanagement.application.messages.UserCreatedMessage;
import ms.usermanagement.application.usecase.address.output.CreateAddressInput;
import ms.usermanagement.application.usecase.user.CreateUserUseCase;
import ms.usermanagement.application.usecase.user.Input.CreateUserInput;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class CreateUserConsumer extends Consumer<UserCreatedMessage> {

    private final CreateUserUseCase createUserUseCase;

    @Override
    @RabbitListener(queues = "${amqp.queue.user-created}")
    public void onMessage(UserCreatedMessage message) {
        try {
            final CreateUserInput input = new CreateUserInput(
                    UUID.fromString(message.id()),
                    message.document(),
                    message.email(),
                    message.name(),
                    message.phone(),
                    message.createdAt(),
                    message.addresses()
                            .stream()
                            .map(a -> new CreateAddressInput(
                                    a.street(),
                                    a.number(),
                                    a.city(),
                                    a.state(),
                                    a.zipCode()
                            )).toList());
            createUserUseCase.execute(input);
        } catch (IllegalArgumentException e) {
            log.info("Erro ao processar mensagem de criação de usuário: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Erro ao criar usuario, envio de email", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}

