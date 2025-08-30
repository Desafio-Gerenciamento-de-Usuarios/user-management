package ms.usermanagement.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.usermanagement.domain.model.Address;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "ENDERECOS", schema = "management")
public class AddressEntity {

    @Id
    @Column(name = "id_endereco")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserEntity user;

    @Column(name = "logradouro")
    private String brand;

    @Column(name = "numero")
    private String number;

    @Column(name = "cidade")
    private String city;

    @Column(name = "estado")
    private String state;

    @Column(name = "cep")
    private String zipCode;

    @Column(name = "dt_criacao")
    private LocalDateTime createdAt;

    public AddressEntity() {
    }

    public static AddressEntity toEntity(Address address) {
        AddressEntity entity = new AddressEntity();
        entity.setId(address.id());
        entity.setBrand(address.street());
        entity.setNumber(address.number());
        entity.setCity(address.city());
        entity.setState(address.state());
        entity.setZipCode(address.zipCode());
        entity.setCreatedAt(address.createdAt());
        return entity;
    }

    public Address toDomain() {
        return new Address(
                this.getId(),
                this.getBrand(),
                this.getNumber(),
                this.getCity(),
                this.getState(),
                this.getZipCode(),
                this.getCreatedAt()
        );
    }
}
