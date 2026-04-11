package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.dtos.project.ProjectRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectResponse;
import com.genxai.AiCodeGenerator.dtos.project.ProjectSummaryResponse;
import com.genxai.AiCodeGenerator.services.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {
    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        return List.of();
    }

    @Override
    public ProjectResponse getProjectById(Long id, Long projectId) {
        return null;
    }

    @Override
    public ProjectResponse updateProject(Long userId, Long projectId, ProjectRequest projectRequest) {
        return null;
    }

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest, Long userId) {
        return null;
    }

    @Override
    public void softDelete(Long userId, Long id) {

    }
}
