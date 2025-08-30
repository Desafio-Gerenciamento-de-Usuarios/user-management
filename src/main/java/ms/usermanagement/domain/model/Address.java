package ms.usermanagement.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Address {
    private final UUID id;
    private final String road;
    private final String number;
    private final String city;
    private final String state;
    private final String zipCode;
    private LocalDateTime createdAt;

    public Address(UUID id, String road, String number, String city, String state, String zipCode, LocalDateTime createdAt) {
        validate(id, road, number, city, state, zipCode);
        this.id = id;
        this.road = road;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.createdAt = createdAt;
    }

    public static Address create(String street, String number, String city, String state, String zipCode) {
        final UUID id = UUID.randomUUID();
        final LocalDateTime createdAt = LocalDateTime.now();
        return new Address(id, street, number, city, state, zipCode, createdAt);
    }

    private static void validate(UUID id, String street, String number, String city, String state, String zipCode) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Number cannot be null or empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("State cannot be null or empty");
        }
        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("Zip code cannot be null or empty");
        }
    }

    public UUID id() {
        return id;
    }

    public String street() {
        return road;
    }

    public String number() {
        return number;
    }

    public String city() {
        return city;
    }

    public String state() {
        return state;
    }

    public String zipCode() {
        return zipCode;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
