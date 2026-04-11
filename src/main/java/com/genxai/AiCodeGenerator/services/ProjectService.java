package com.genxai.AiCodeGenerator.services;

import com.genxai.AiCodeGenerator.dtos.project.ProjectRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectResponse;
import com.genxai.AiCodeGenerator.dtos.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    public List<ProjectSummaryResponse> getUserProjects(Long userId) ;
    public ProjectResponse getProjectById(Long id, Long projectId) ;

    public ProjectResponse updateProject(Long userId, Long projectId, ProjectRequest projectRequest) ;

    public ProjectResponse createProject(ProjectRequest projectRequest, Long userId) ;

    public void softDelete(Long userId, Long id) ;
}
