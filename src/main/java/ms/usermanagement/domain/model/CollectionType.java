package ms.usermanagement.domain.model;

import lombok.Getter;

@Getter
public enum CollectionType {
    INSOMNIA("insomnia"),
    POSTMAN("postman");

    private final String value;

    CollectionType(String value) {
        this.value = value;
    }
}
