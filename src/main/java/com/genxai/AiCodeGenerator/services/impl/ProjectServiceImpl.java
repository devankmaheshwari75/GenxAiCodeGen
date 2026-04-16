package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.dtos.project.ProjectRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectResponse;
import com.genxai.AiCodeGenerator.dtos.project.ProjectSummaryResponse;
import com.genxai.AiCodeGenerator.entities.Project;
import com.genxai.AiCodeGenerator.entities.User;
import com.genxai.AiCodeGenerator.mapper.ProjectMapper;
import com.genxai.AiCodeGenerator.repositories.ProjectRepository;
import com.genxai.AiCodeGenerator.repositories.UserRepository;
import com.genxai.AiCodeGenerator.services.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CurrentTimestamp;
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


    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {

        User user = userRepository.findById(userId).orElseThrow();
        List<Project> activeProjects  = projectRepository.findAllAccessibleByUser(userId);

        return projectMapper.toListOfProjectSummaryResponse(activeProjects);

    }

    @Override
    public ProjectResponse getProjectById(Long id, Long projectId) {

        User user =  userRepository.findById(id).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();


        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long userId, Long projectId, ProjectRequest projectRequest) {

        Project project = projectRepository
                .findAccessibleProjectById(projectId, userId).orElseThrow();

      project.setName(projectRequest.name());
       Project saved  =  projectRepository.save(project);

        return projectMapper.toProjectResponse(project);



    }

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest, Long userId) {

        User user = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder().
                name(projectRequest.name())
                .owner(user)
                .isPublic(false)
                .build();

        projectRepository.save(project);
        return projectMapper.toProjectResponse(project);



    }

    @Override
    public void softDelete(Long userId, Long projectId) {


        int updated = projectRepository.softDelete(userId , projectId);

        if(updated == 0){
            throw new RuntimeException("Project not found or already deleted");

        }


    }
}
