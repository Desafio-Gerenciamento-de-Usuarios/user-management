package ms.usermanagement.application.usecase.collection.input;

import jakarta.servlet.http.HttpServletRequest;
import ms.usermanagement.domain.model.CollectionType;

public record HttpInput(
        CollectionType collectionType,
        String scheme,
        String serverName,
        int serverPort,
        String contextPath
) {
    public static HttpInput from(HttpServletRequest request, CollectionType collectionType) {
        return new HttpInput(
                collectionType,
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                request.getContextPath()
        );
    }
}
