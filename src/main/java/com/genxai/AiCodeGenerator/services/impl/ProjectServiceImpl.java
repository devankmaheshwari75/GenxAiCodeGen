package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.config.AuthUtil;
import com.genxai.AiCodeGenerator.dtos.project.ProjectRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectResponse;
import com.genxai.AiCodeGenerator.dtos.project.ProjectSummaryResponse;
import com.genxai.AiCodeGenerator.entities.*;
import com.genxai.AiCodeGenerator.mapper.ProjectMapper;
import com.genxai.AiCodeGenerator.repositories.ProjectMemberRepository;
import com.genxai.AiCodeGenerator.repositories.ProjectRepository;
import com.genxai.AiCodeGenerator.repositories.UserRepository;
import com.genxai.AiCodeGenerator.services.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
public class ProjectServiceImpl implements ProjectService {

    UserRepository userRepository;
    ProjectMapper projectMapper ;
    ProjectRepository projectRepository;
    AuthUtil authUtil;
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {

        Long userId = authUtil.getCurrentUserId();

//        User user = userRepository.findById(userId).orElseThrow();



        List<Project> activeProjects  = projectRepository.findAllAccessibleByUser(userId);

        return projectMapper.toListOfProjectSummaryResponse(activeProjects);

    }

    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")
    public ProjectResponse getProjectById(Long projectId) {

        Long userId = authUtil.getCurrentUserId();

//        User user =  userRepository.findById(id).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();

        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canEditProject(#projectId)")
    public ProjectResponse updateProject(Long projectId, ProjectRequest projectRequest) {

        Long userId = authUtil.getCurrentUserId();

        Project project = projectRepository
                .findAccessibleProjectById(projectId, userId).orElseThrow();

      project.setName(projectRequest.name());
       Project saved  =  projectRepository.save(project);

        return projectMapper.toProjectResponse(saved);



    }

    @Override

    public ProjectResponse createProject(ProjectRequest projectRequest) {
        Long userId  = authUtil.getCurrentUserId();

//        User user = userRepository.findById(userId).orElseThrow();

        User owner = userRepository.getReferenceById(userId);


        Project project = Project.builder().
                name(projectRequest.name())
                .isPublic(false)
                .build();

       project =  projectRepository.save(project);
//         projectMapper.toProjectResponse(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId() , owner.getId());

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);


    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#projectId)")
    public void softDelete( Long projectId) {

        Long userId = authUtil.getCurrentUserId();

        int updated = projectRepository.softDelete(userId, projectId);

        if(updated == 0){
            throw new RuntimeException("Project not found or already deleted");

        }


    }
}
