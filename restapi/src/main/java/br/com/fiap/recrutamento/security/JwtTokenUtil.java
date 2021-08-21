package br.com.fiap.recrutamento.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${fiap.secret}")
    private String secret;

    @Value("${fiap.expireInSeconds}")
    private int expireInSeconds;

    public String generateToken(String nome){
        Date dataCriacao = getDateFrom(LocalDateTime.now());
        Date dataExpiracao = getDateFrom(LocalDateTime.now().plusSeconds(expireInSeconds));
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(dataCriacao)
                .setExpiration(dataExpiracao)
                .setSubject(nome)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUserFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    private Date getDateFrom(LocalDateTime now) {
        return Date.from(now.toInstant(OffsetDateTime.now().getOffset()));
    }


}
