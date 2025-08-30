package ms.usermanagement.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String document;
    private String email;
    private String name;
    private String phone;
    private LocalDateTime createdAt;
    private List<Address> addresses = new ArrayList<>();

    public User(UUID id, String document, String email, String name, String phone, List<Address> addresses, LocalDateTime createdAt) {
        validate(document, email, name, phone, addresses);
        this.id = id;
        this.document = document;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.createdAt = createdAt;
        this.addresses = addresses;
    }

    public static User create(UUID id, String document, String email, String name, String phone, List<Address> addresses) {
        final LocalDateTime now = LocalDateTime.now();
        return new User(id, document, email, name, phone, addresses, now);
    }

    private void validate(String document, String email, String name, String phone, List<Address> addresses) {
        if (document == null || document.isEmpty()) {
            throw new IllegalArgumentException("Document is required");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone is required");
        }
        if (addresses == null || addresses.isEmpty()) {
            throw new IllegalArgumentException("At least one address is required");
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
