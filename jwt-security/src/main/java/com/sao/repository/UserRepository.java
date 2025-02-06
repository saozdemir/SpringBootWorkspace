package com.sao.repository;

import com.sao.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 Şub 2025
 * <p>
 * @description:
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@Query(value = "from User where username =:username")
    Optional<User> findByUsername(String username);
}
