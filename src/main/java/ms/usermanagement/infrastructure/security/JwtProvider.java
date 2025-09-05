package ms.usermanagement.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import ms.usermanagement.domain.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class JwtProvider {

    private final RSAPublicKey publicKey;

    @Autowired
    public JwtProvider(
            @Value("${jwt.public-key-path}") String publicKeyPath
    ) throws Exception {
        this.publicKey = loadPublicKeyFromFile(publicKeyPath);
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    private RSAPublicKey loadPublicKeyFromFile(String filePath) throws Exception {
        String keyContent = Files.readString(Paths.get(filePath));
        byte[] keyBytes = stripPemHeaderFooter(keyContent);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    private byte[] stripPemHeaderFooter(String keyContent) {
        String clean = keyContent
                .replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)-----", "")
                .replaceAll("\\s", "");
        return Base64.getDecoder().decode(clean);
    }

    private DecodedJWT verifyToken(String token) {
        try {
            final Algorithm algorithm = Algorithm.RSA256(publicKey, null);
            final JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTDecodeException e) {
            throw new InvalidTokenException("Formato do token inválido: " + e.getMessage(), e);
        } catch (com.auth0.jwt.exceptions.TokenExpiredException e) {
            throw new TokenExpiredException("Token expirado", e.getExpiredOn());
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException("Token JWT inválido: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new InvalidTokenException("Erro ao verificar token", e);
        }
    }
}
