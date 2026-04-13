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
public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query("""
            SELECT p from Project p where p.deletedAt is null and p.owner.id = :userId order by p.updatedAt desc
            """)
    List<Project> findAllAccessibleByUser(@Param("userId") Long userId);


    @Modifying
    @Query("""
             update Project p set p.deletedAt = CURRENT_TIMESTAMP where p.id = :projectId and p.owner.id =:userId
             and p.deletedAt is null   
             """)
    int softDelete(@Param("userId") Long userId, @Param("projectId") Long projectId);




    @Query("""
            Select p from Project p left join fetch p.owner
            where p.id = :projectId and 
            p.deletedAt is null and p.owner.id =:userId
            
            """)
    Optional<Project> findAccessibleProjectById(@Param("projectId") Long projectId , @Param("userId") Long userId);


}