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


        return ResponseEntity.ok(projectMemberService.getAllMembers(projectId , userId));



    }

    @PostMapping()
    public ResponseEntity<ProjectMemberResponse> inviteByEmail(@PathVariable Long projectId , @RequestBody @Valid  InviteMemberRequest request ){

        Long userId =1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.inviteMember(projectId, request , userId));

    }


    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateRoleOfMemberInProject(@PathVariable Long projectId , @PathVariable Long memberId , @RequestBody UpdateMemberRoleRequest request){

        Long userId = 1L;
        projectMemberService.updateMemberRole(userId , projectId , memberId , request);

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("/{memberId}")

    public ResponseEntity<Void> deleteMember(@PathVariable Long projectId , @PathVariable Long memberId){

    projectMemberService.deleteMember(projectId , memberId);

    return ResponseEntity.noContent().build();


    }





}
