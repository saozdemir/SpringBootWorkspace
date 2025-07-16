package com.sao.usermanagement.repository;

import com.sao.usermanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
