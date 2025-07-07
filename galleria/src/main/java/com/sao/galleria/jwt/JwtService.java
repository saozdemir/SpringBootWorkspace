package com.sao.galleria.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description: Bu sınıf, JWT (JSON Web Token) işlemlerini yönetir.
 */
@Service
public class JwtService {
    public static final String SECRET_KEY = "ogAU+Rgiu5A3EIbcVpccXU5I8kVtos5WdO4QAcJIHzs=";

    /**
     * Kullanıcı bilgilerini alır ve JWT token oluşturur.
     * getKey() metodu ile güvenli anahtar elde edilir.
     * @param userDetails Kullanıcı bilgileri
     * @return Oluşturulan JWT token
     */
    private String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))// 2 hours expiration
                .signWith(SignatureAlgorithm.HS256, getKey())
                .compact();
    }

    /**
     * Güvenli anahtar oluşturur. Bu anahtar JWT imzalamada kullanılır.
     * @return
     */
    public Key getKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    /**
     * Bu metot, token'ı alır ve içinde bulunan bilgileri döner.
     * Claims, token içindeki verileri temsil eder. Bu veriler yetki, role gibi bilgileri içerebilir.
     * @param token
     * @return claims
     */
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Bu exportToken metodu, verilen JWT token'ı çözümler ve içindeki Claims (token'a gömülü veriler) nesnesini alır.
     * Ardından, dışarıdan verilen bir fonksiyonu (claimsFunction) bu Claims nesnesine uygular ve sonucu döndürür.
     * Böylece token içindeki verilere esnek şekilde erişim sağlar.
     * Örneğin, token'dan kullanıcı adını veya başka bir bilgiyi çekmek için kullanılabilir.
     * @param token
     * @return Kullanıcı adı
     */
    public <T> T exportToken(String token, Function<Claims, T> claimsFunction) {
        final Claims claims = getClaims(token);
        return claimsFunction.apply(claims);
    }

    /**
     * Doğrudan kullanıcı adını almak için kullanılır.
     * generateToken() metodu içinde set edilen kullanıcı adı (setSubject) bilgisi kullanılır.
     * @param token
     * @return username
     */
    public String getUsernameByToken(String token) {
        return exportToken(token, Claims::getSubject);// getSubject, token içindeki kullanıcı adını alır.
    }

    /**
     * Token'ın geçerliliğini kontrol eder.
     * generateToken() metodu içinde set edilen (setExpiration) son kullanma tarihini kullanır.
     * @param token
     * @return
     */
    public boolean isTokenValid(String token) {
        Date expireDate = exportToken(token, Claims::getExpiration);// Token'ın son kullanma tarihini alır.
        return new Date().before(expireDate);
    }
}
