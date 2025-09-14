package ms.usermanagement.application.usecase.collection.input;

import jakarta.servlet.http.HttpServletRequest;

public record HttpInput(
        String scheme,
        String serverName,
        int serverPort,
        String contextPath
) {
    public static HttpInput from(HttpServletRequest request) {
        return new HttpInput(
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                request.getContextPath()
        );
    }
}
