package ms.usermanagement.application.usecase.address.output;

public record CreateAddressInput(
        String read,
        String number,
        String city,
        String state,
        String zipCode
) {
}
