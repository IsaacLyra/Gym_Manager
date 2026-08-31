package br.com.firstclass.spring_boot_essentials.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("${jwt.expiration}")
    private Long expirationTime;


    @Value("${jwt.key}")
    private String key;

    // Gerar um token

    public String gerarToken(Authentication authentication){
       UserDetails user = (UserDetails)authentication.getPrincipal(); //Salvar todo o objeto do user
        return buildToken(user.getUsername());

    }

    private String buildToken(String username){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey(){
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    // Validar um token


    public Boolean isTokenValid(String token){

        try {
            getClaims(token);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    // extrair informações do token
    public String getUsername(String token){
        return getClaims(token).getSubject(); // username
    }


    private Claims getClaims(String token){

        // validar assinatura
        //  validar expiracao
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
