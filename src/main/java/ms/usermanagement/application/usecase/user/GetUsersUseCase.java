package ms.usermanagement.application.usecase.user;

import ms.usermanagement.application.usecase.VoidInputUseCase;
import ms.usermanagement.application.usecase.user.output.GetUsersOutput;
import ms.usermanagement.domain.gateway.UserGateway;
import ms.usermanagement.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUsersUseCase extends VoidInputUseCase<GetUsersOutput> {

    private final UserGateway userGateway;

    public GetUsersUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public GetUsersOutput execute() {
        final List<User> users = userGateway.findAll();
        return GetUsersOutput.from(users);
    }
}
