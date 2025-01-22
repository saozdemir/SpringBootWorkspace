package com.sao.repository;

import com.sao.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description:
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
