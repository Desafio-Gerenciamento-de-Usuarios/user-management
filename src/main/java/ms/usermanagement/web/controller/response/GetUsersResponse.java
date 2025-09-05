package ms.usermanagement.web.controller.response;

import ms.usermanagement.application.usecase.user.output.GetUsersOutput;

import java.util.stream.Stream;

public record GetUsersResponse(
        Stream<UserResponse> users
) {
    public static Stream<UserResponse> from(GetUsersOutput output) {
        return output.users().stream()
                .map(UserResponse::from);
    }
}
