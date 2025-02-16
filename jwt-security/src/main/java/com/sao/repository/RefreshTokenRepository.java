package com.sao.repository;

import com.sao.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Ref;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Şub 2025
 * <p>
 * @description:
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    //@Query(value = "from RefreshToken r where r.refreshToken = :refreshToken")
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
