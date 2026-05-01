package com.genxai.AiCodeGenerator.services;

import com.genxai.AiCodeGenerator.dtos.project.InviteMemberRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectMemberResponse;
import com.genxai.AiCodeGenerator.dtos.project.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    public List<ProjectMemberResponse> getAllMembers(Long projectId) ;

    public ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    public ProjectMemberResponse updateMemberRole( Long projectId, Long memberId, UpdateMemberRoleRequest request) ;

    public void deleteMember(Long projectId, Long memberId) ;
}
