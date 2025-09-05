package ms.usermanagement.application.usecase.user.output;

import ms.usermanagement.domain.model.User;

import java.util.List;

public record GetUsersOutput(
        List<User> users
) {
    public static GetUsersOutput from(List<User> users) {
        return new GetUsersOutput(users);
    }
}
