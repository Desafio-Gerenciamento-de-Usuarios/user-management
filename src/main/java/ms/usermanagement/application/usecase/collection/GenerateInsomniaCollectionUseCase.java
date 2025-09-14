package ms.usermanagement.application.usecase.collection;

import ms.usermanagement.application.usecase.UseCase;
import ms.usermanagement.application.usecase.collection.input.HttpInput;
import ms.usermanagement.domain.gateway.CollectionGateway;
import org.springframework.stereotype.Service;

@Service
public class GenerateInsomniaCollectionUseCase extends UseCase<HttpInput, String> {

    private final CollectionGateway collectionGateway;

    public GenerateInsomniaCollectionUseCase(CollectionGateway collectionGateway) {
        this.collectionGateway = collectionGateway;
    }

    @Override
    public String execute(HttpInput input) {
        final String collection = collectionGateway.getInsomniaCollection();

        final String baseUrl = input.scheme() + "://" + input.serverName()
                + (input.serverPort() == 80 || input.serverPort() == 443 ? "" : ":" + input.serverPort())
                + input.contextPath();

        return collection.replace("{{ hostUser }}", baseUrl);
    }

    @Override
    public void validate(HttpInput input) {
        if (input == null) {
            throw new IllegalArgumentException("HttpInput não pode ser nulo");
        }
        if (input.serverName() == null || input.serverName().isBlank()) {
            throw new IllegalArgumentException("ServerName não pode ser vazio");
        }
    }
}
