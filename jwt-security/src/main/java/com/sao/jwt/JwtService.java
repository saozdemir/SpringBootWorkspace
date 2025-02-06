package com.sao.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 Şub 2025
 * <p>
 * @description: Bu servis sınıfı token oluşturma ve çözme işlemlerini gerçekleştirir.
 */
@Component
public class JwtService {

    /**
     * Burada bir anahtar oluşturduk ve bu anahtarı kullanarak token oluşturacağız.
     * https://8gwifi.org/jwsgen.jsp
     */
    public static final String SECRET_KEY = "7V8SwMsis8jId2a6LPWHxRykH9QkZwpdBLhi+9cEZNE=";


    /**
     * Token oluşturma servisi sıraıyla aşağıdaki değerler set edildi:
     * Kullanıcı adı,
     * Oluşturma tarihi,
     * Geçerlilik tarihi, (2 saat olacak şekilde: 1000*60*60*2)
     * Bir secret key ve algoritma kullanarak oluşturulan imza ile hazırlandı.
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        /** setClaims metodu ile map oluşturup yetki tanımlarını bu map ile gönderebiliriz.*/
        Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("role", "ADMIN");
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setClaims(claimsMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generic bir token çözücü metodudur.
     * Aynı key'i kullanarak parse eder.
     * Parse ederken döndürülecek tip username için string,
     * geçerlilik süresi vb için Date olacağından generic oluşturuldu.
     * @param token
     * @param claimsFunction
     * @return
     * @param <T>
     */
    public <T> T exportToken(String token, Function<Claims, T> claimsFunction) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();

        return claimsFunction.apply(claims);
    }

    public String getUserNameByToken(String token) {
        return exportToken(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expiredDate =  exportToken(token, Claims::getExpiration);
        return new Date().before(expiredDate);
    }

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
