package ms.usermanagement.infrastructure.gateway;

import ms.usermanagement.domain.gateway.UserGateway;
import ms.usermanagement.domain.model.User;
import ms.usermanagement.infrastructure.entity.UserEntity;
import ms.usermanagement.infrastructure.gateway.repository.UserRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGatewayImpl implements UserGateway {

    private final UserRepositoryJpa userRepositoryJpa;

    public UserGatewayImpl(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepositoryJpa.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        final UserEntity entity = UserEntity.toEntity(user);
        userRepositoryJpa.save(entity);
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public boolean existByDocument(String document) {
        return userRepositoryJpa.existsByDocument(document);
    }

    @Override
    public List<User> findAll() {
        final var entities = userRepositoryJpa.findAll();
        return entities.stream().map(UserEntity::toDomain).toList();
    }
}
