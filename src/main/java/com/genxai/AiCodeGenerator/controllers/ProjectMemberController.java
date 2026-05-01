package com.genxai.AiCodeGenerator.controllers;

import com.genxai.AiCodeGenerator.dtos.project.InviteMemberRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectMemberResponse;
import com.genxai.AiCodeGenerator.dtos.project.UpdateMemberRoleRequest;
import com.genxai.AiCodeGenerator.entities.ProjectMember;
import com.genxai.AiCodeGenerator.services.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/projects/{projectId}/members")
@RestController
@RequiredArgsConstructor


public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<ProjectMemberResponse>> getProjectMembers(@PathVariable Long projectId ){

        Long userId = 1L;

        return ResponseEntity.ok(projectMemberService.getAllMembers(projectId));



    }

    @PostMapping()
    public ResponseEntity<ProjectMemberResponse> inviteByEmail(@PathVariable Long projectId , @RequestBody @Valid  InviteMemberRequest request ){


        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.inviteMember(projectId, request ));

    }


    @PatchMapping("/{memberId}")
    public ResponseEntity<ProjectMemberResponse> updateRoleOfMemberInProject(@PathVariable Long projectId , @PathVariable Long memberId , @RequestBody UpdateMemberRoleRequest request){

        ;

        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId , memberId , request));

    }


    @DeleteMapping("/{memberId}")

    public ResponseEntity<Void> deleteMember(@PathVariable Long projectId , @PathVariable Long memberId){

    projectMemberService.deleteMember(projectId , memberId);

    return ResponseEntity.noContent().build();


    }





}
