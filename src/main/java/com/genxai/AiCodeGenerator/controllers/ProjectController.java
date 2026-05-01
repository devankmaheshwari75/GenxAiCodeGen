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


        return ResponseEntity.ok(projectService.getUserProjects());


    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long projectId){




        return ResponseEntity.ok(projectService.getProjectById(  projectId));



    }
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest projectRequest){


        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(projectRequest  ));



    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId , @RequestBody @Valid  ProjectRequest projectRequest){


        return ResponseEntity.ok(projectService.updateProject(  projectId , projectRequest));

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteProject(@PathVariable Long id){

        projectService.softDelete( id);


        return ResponseEntity.noContent().build();

    }








}
