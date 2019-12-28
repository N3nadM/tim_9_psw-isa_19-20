package com.isapsw.Projekat.security;

import com.isapsw.Projekat.domain.Korisnik;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.isapsw.Projekat.security.Konstante.EXPIRE;
import static com.isapsw.Projekat.security.Konstante.SECRET;

@Component
public class JWTTokenProvider {

    public String generate(Authentication authentication){
        Korisnik korisnik = (Korisnik)authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + EXPIRE);

        String userId = Long.toString(korisnik.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(korisnik.getId())));
        claims.put("username", korisnik.getEmail());
        claims.put("firstLogin", korisnik.getDateModified() == null);
        claims.put("role", korisnik.getAuthorities());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean validate(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    public String getUserUsernameFromJWT(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            String username = (String)claims.get("username");
            return username;
        } catch (Exception e) {
            return null;
        }
    }

}
