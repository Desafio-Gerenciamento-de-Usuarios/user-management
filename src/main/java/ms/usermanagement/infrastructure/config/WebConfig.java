package ms.usermanagement.infrastructure.config;

import ms.usermanagement.application.facades.MessageFacade;
import ms.usermanagement.application.usecase.user.CreateUserUseCase;
import ms.usermanagement.domain.gateway.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class WebConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserGateway userGateway, MessageFacade messageFacade, TemplateEngine templateEngine, ServerInfo serverInfo) {
        return new CreateUserUseCase(userGateway, messageFacade, templateEngine, serverInfo);
    }

    @Bean
    public TemplateEngine templateEngine() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/"); // pasta dentro de resources
        resolver.setSuffix(".html");      // extensão dos templates
        resolver.setTemplateMode("HTML5"); // modo HTML
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);     // para desenvolvimento, pode deixar true em produção

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
    }
}
