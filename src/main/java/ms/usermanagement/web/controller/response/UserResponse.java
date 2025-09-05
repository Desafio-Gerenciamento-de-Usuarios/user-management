package ms.usermanagement.web.controller.response;

import ms.usermanagement.domain.model.User;

import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String document,
        String email,
        String name,
        String phone,
        String createdAt,
        List<AddressResponse> addresses
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getDocument(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getCreatedAt().toString(),
                user.getAddresses().stream()
                        .map(address -> new AddressResponse(
                                address.street(),
                                address.number(),
                                address.city(),
                                address.state(),
                                address.zipCode()
                        ))
                        .toList()
        );
    }
}
