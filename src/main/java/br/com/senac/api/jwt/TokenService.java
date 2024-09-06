package br.com.senac.api.jwt;

import br.com.senac.api.entitys.Usuarios;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${spring.security.token.secret}")
    private String secret;
    @Value("${spring.security.token.expiration_time}")
    private Long expirationTime;

    public String gerarToken(Usuarios usuario) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("exemplo-security-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(this.gerarExpiracaoData())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException ex) {
            ex.printStackTrace();
            throw new Exception("Erro ao gerar token!");
        }
    }

    public String validaToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("exemplo-security-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            return null;
        }
    }

    private Instant gerarExpiracaoData() {
        return LocalDateTime.now().plusHours(expirationTime).toInstant(ZoneOffset.of("-03:00"));
    }
}

