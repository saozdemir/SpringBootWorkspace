package com.sao.threadperformance.repository;

import com.sao.threadperformance.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 06 May 2025
 * <p>
 * @description:
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM thread.task WHERE name LIKE %:prefix%", nativeQuery = true)
    List<Task> findByNameContaining(@Param("prefix") String prefix);

}
