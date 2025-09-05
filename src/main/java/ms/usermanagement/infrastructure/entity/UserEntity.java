package ms.usermanagement.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.usermanagement.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "USUARIOS", schema = "management")
public class UserEntity {

    @Id
    @Column(name = "ID_USUARIO")
    private UUID id;

    @Column(name = "DOCUMENTO", unique = true)
    private String document;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NOME")
    private String name;

    @Column(name = "TELEFONE")
    private String phone;

    @Column(name = "DT_CRIACAO")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AddressEntity> address;

    public UserEntity() {
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setDocument(user.getDocument());
        entity.setEmail(user.getEmail());
        entity.setName(user.getName());
        entity.setPhone(user.getPhone());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setAddress(
                user.getAddresses()
                        .stream()
                        .map(address -> AddressEntity.toEntity(address, entity))
                        .toList()
        );

        return entity;
    }


    public User toDomain() {
        return new User(
                this.getId(),
                this.getDocument(),
                this.getEmail(),
                this.getName(),
                this.getPhone(),
                this.getAddress()
                        .stream()
                        .map(AddressEntity::toDomain)
                        .toList(),
                this.getCreatedAt()
        );
    }
}
