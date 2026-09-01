package br.com.firstclass.spring_boot_essentials.config;

import dto.TokenResponseDto;
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

    @Value("${jwt.expiration:900000}")
    private Long expirationTime = 900000L;

    @Value("${jwt.key}")
    private String key;


    public TokenResponseDto gerarToken(Authentication authentication){
       UserDetails user = (UserDetails)authentication.getPrincipal(); //Salvar todo o objeto do user
        String token = buildToken(user.getUsername()); // ADICIONADO

        return new TokenResponseDto(token, expirationTime);
    }

    private String buildToken(String username){
        Date now = new Date();
        long exp = (expirationTime != null) ? expirationTime : 900000L;
        Date expiration = new Date(now.getTime() + exp);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey(){
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
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
