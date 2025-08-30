package ms.usermanagement.application.usecase.user;

import ms.usermanagement.application.usecase.VoidUseCase;
import ms.usermanagement.application.usecase.user.Input.CreateUserInput;
import ms.usermanagement.domain.gateway.UserGateway;
import ms.usermanagement.domain.model.Address;
import ms.usermanagement.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase extends VoidUseCase<CreateUserInput> {

    final UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void execute(CreateUserInput input) {
        validate(input);

        final User domain = User.create(
                input.id(),
                input.document(),
                input.email(),
                input.name(),
                input.phone(),
                input.addresses()
                        .stream()
                        .map(a -> Address.create(
                                a.read(),
                                a.number(),
                                a.city(),
                                a.state(),
                                a.zipCode()
                        )).toList());


        userGateway.save(domain);
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
}
