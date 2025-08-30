package ms.usermanagement.domain.gateway;

import ms.usermanagement.domain.model.User;

public interface UserGateway {
    boolean existsByEmail(String email);

    void save(User user);

    User findById(String id);

    boolean existByDocument(String document);
}

