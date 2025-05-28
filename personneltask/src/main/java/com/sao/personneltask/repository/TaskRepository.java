package com.sao.personneltask.repository;

import com.sao.personneltask.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 May 2025
 * <p>
 * @description:
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Finds tasks by personnel ID.
     *
     * @param personnelId the ID of the personnel
     * @return a list of tasks associated with the given personnel ID
     */

    @Query("SELECT t FROM Task t WHERE t.personnelId = :personnelId")
    List<Task> findByPersonnelId(@Param("personnelId") Long personnelId);
}
