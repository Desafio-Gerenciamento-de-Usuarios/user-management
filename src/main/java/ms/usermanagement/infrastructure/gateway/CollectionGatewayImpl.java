package ms.usermanagement.infrastructure.gateway;

import ms.usermanagement.domain.gateway.CollectionGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CollectionGatewayImpl implements CollectionGateway {

    @Value("${app.collection.path.insomnia}")
    private String collectionPath;

    @Override
    public String getInsomniaCollection() {
        try {
            return Files.readString(Path.of(collectionPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
