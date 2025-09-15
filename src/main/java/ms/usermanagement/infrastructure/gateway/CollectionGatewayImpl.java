package ms.usermanagement.infrastructure.gateway;

import ms.usermanagement.domain.gateway.CollectionGateway;
import ms.usermanagement.domain.model.CollectionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Service
public class CollectionGatewayImpl implements CollectionGateway {

    private final Map<CollectionType, String> collectionPaths;

    public CollectionGatewayImpl(
            @Value("${app.collection.path.insomnia}") String insomniaPath,
            @Value("${app.collection.path.postman}") String postmanPath
    ) {
        this.collectionPaths = Map.of(
                CollectionType.INSOMNIA, insomniaPath,
                CollectionType.POSTMAN, postmanPath
        );
    }

    @Override
    public String getCollection(CollectionType collectionType) {
        String path = collectionPaths.get(collectionType);

        if (path == null) {
            throw new IllegalArgumentException("Collection desconhecida: " + collectionType);
        }

        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Erro ao ler a collection: " + collectionType + " no caminho " + path, e
            );
        }
    }
}
