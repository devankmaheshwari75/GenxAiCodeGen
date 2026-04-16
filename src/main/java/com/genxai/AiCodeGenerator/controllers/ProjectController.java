package com.genxai.AiCodeGenerator.controllers;

import com.genxai.AiCodeGenerator.dtos.project.ProjectRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectResponse;
import com.genxai.AiCodeGenerator.dtos.project.ProjectSummaryResponse;
import com.genxai.AiCodeGenerator.services.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/projects")
@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public ResponseEntity<List<ProjectSummaryResponse>> getAllUserProjects(){

        Long userId = 1L;
        return ResponseEntity.ok(projectService.getUserProjects(userId));


    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long projectId){

        Long userId = 1L;


        return ResponseEntity.ok(projectService.getProjectById(userId , projectId));



    }
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest projectRequest){

        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(projectRequest , userId));



    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId , @RequestBody @Valid  ProjectRequest projectRequest){

        Long userId = 1L;
        return ResponseEntity.ok(projectService.updateProject(userId , projectId , projectRequest));

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        Long userId = 1L;
        projectService.softDelete(userId , id);


        return ResponseEntity.noContent().build();

    }








}
