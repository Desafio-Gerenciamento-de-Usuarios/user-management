package ms.usermanagement.infrastructure.config;

import ms.usermanagement.application.usecase.user.CreateUserUseCase;
import ms.usermanagement.domain.gateway.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserGateway userGateway) {
        return new CreateUserUseCase(userGateway);
    }
}
