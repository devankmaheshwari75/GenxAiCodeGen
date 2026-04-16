package com.genxai.AiCodeGenerator.repositories;

import com.genxai.AiCodeGenerator.entities.ProjectMember;
import com.genxai.AiCodeGenerator.entities.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {


//    @Query("""
//            Select p from ProjectMember p
//            where p.member_id.projectid =:projectId
//            """)
    List<ProjectMember> findByIdProjectId(@PathVariable("projectId") Long projectId);
    boolean existsByIdProjectIdAndIdUserId(Long projectId, Long userId);

    ProjectMember findByIdProjectIdAndIdUserId(Long projectId, Long userId);

    void deleteByIdProjectIdAndUserId(Long projectId, Long memberId);
}
