package ms.usermanagement.web.controller.response;

public record AddressResponse(
        String street,
        String number,
        String city,
        String state,
        String zipCode
) {
}
