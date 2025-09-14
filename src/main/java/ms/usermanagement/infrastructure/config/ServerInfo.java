package ms.usermanagement.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ServerInfo {

    private final WebServerApplicationContext context;

    @Value("${server.address:localhost}")
    private String host;

    @Value("${server.ssl.enabled:false}")
    private boolean sslEnabled;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public ServerInfo(WebServerApplicationContext context) {
        this.context = context;
    }

    public String getBaseUrl() {
        int port = context.getWebServer().getPort();
        String scheme = sslEnabled ? "https" : "http";
        return scheme + "://" + host + ":" + port + contextPath;
    }
}
