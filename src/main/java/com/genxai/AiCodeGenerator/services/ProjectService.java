package com.genxai.AiCodeGenerator.services;

import com.genxai.AiCodeGenerator.dtos.project.ProjectRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectResponse;
import com.genxai.AiCodeGenerator.dtos.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    public List<ProjectSummaryResponse> getUserProjects() ;
    public ProjectResponse getProjectById( Long projectId) ;

    public ProjectResponse updateProject(Long projectId, ProjectRequest projectRequest) ;

    public ProjectResponse createProject(ProjectRequest projectRequest) ;

    public void softDelete( Long id) ;
}
