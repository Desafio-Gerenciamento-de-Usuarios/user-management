package ms.usermanagement.domain.gateway;

import ms.usermanagement.domain.model.CollectionType;

public interface CollectionGateway {
    String getCollection(CollectionType nameCollection);
}
