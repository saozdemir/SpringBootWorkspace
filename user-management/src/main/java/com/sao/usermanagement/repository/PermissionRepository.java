package com.sao.usermanagement.repository;

import com.sao.usermanagement.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
