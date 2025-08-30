package ms.usermanagement.application.messages;

import java.io.Serializable;
import java.util.List;

public record UserCreatedMessage(
        String id,
        String document,
        String email,
        String name,
        String phone,
        String createdAt,
        List<Address> addresses
) implements Serializable {
    public record Address(
            String street,
            String number,
            String city,
            String state,
            String zipCode
    ) implements Serializable {
        public static UserCreatedMessage.Address from(ms.usermanagement.domain.model.Address address) {
            return new UserCreatedMessage.Address(
                    address.street(),
                    address.number(),
                    address.city(),
                    address.state(),
                    address.zipCode()
            );
        }
    }
}

