package com.genxai.AiCodeGenerator.repositories;

import com.genxai.AiCodeGenerator.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface  ProjectRepository extends JpaRepository<Project, Long> {


    @Query("""
           Select p as project , pm.projectRole as role 
           From Project p JOIN ProjectMember  pm 
           ON pm.project.id = p.id
           WHERE pm.user.id =:userId 
           AND p.deletedAt IS NULL
           ORDER BY p.updatedAt DESC
           
            """)
    List<Project> findAllAccessibleByUser(@Param("userId") Long userId);


    @Modifying
    @Query("""
             update Project p set p.deletedAt = CURRENT_TIMESTAMP where p.id = :projectId 
             AND p.deletedAt is null   
             """)
    int softDelete(@Param("userId") Long userId, @Param("projectId") Long projectId);




    @Query("""
            SELECT p FROM Project p
            WHERE p.id = :projectId
                AND p.deletedAt IS NULL
                AND EXISTS (
                    SELECT 1 FROM ProjectMember pm
                    WHERE pm.id.userId = :userId
                    AND pm.id.projectId = :projectId
                )
            """)
    Optional<Project> findAccessibleProjectById(@Param("projectId") Long projectId , @Param("userId") Long userId);


}