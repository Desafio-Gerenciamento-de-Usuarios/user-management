package ms.usermanagement.infrastructure.gateway.repository;

import ms.usermanagement.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByDocument(String document);
}
