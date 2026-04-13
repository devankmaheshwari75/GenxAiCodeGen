package com.genxai.AiCodeGenerator.mapper;

import com.genxai.AiCodeGenerator.dtos.project.ProjectResponse;
import com.genxai.AiCodeGenerator.dtos.project.ProjectSummaryResponse;
import com.genxai.AiCodeGenerator.entities.Project;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);









}
