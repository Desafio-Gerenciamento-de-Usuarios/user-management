package ms.usermanagement.application.usecase.user.Input;

import ms.usermanagement.application.usecase.address.output.CreateAddressInput;

import java.util.List;
import java.util.UUID;

public record CreateUserInput(
        UUID id,
        String document,
        String email,
        String name,
        String phone,
        String createdAt,
        List<CreateAddressInput> addresses
) {
}
