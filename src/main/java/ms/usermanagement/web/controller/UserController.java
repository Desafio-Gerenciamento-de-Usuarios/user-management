package ms.usermanagement.web.controller;

import ms.usermanagement.application.usecase.user.GetUsersUseCase;
import ms.usermanagement.application.usecase.user.output.GetUsersOutput;
import ms.usermanagement.web.controller.response.GetUsersResponse;
import ms.usermanagement.web.controller.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetUsersUseCase getUsersUseCase;

    public UserController(GetUsersUseCase getUsersUseCase) {
        this.getUsersUseCase = getUsersUseCase;
    }

    @GetMapping
    public ResponseEntity<Stream<UserResponse>> getUsers() {

        final GetUsersOutput output = getUsersUseCase.execute();

        final Stream<UserResponse> response = GetUsersResponse.from(output);

        return ResponseEntity.ok(response);
    }

}
