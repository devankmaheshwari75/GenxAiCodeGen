package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.dtos.project.InviteMemberRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectMemberResponse;
import com.genxai.AiCodeGenerator.dtos.project.UpdateMemberRoleRequest;
import com.genxai.AiCodeGenerator.services.ProjectMemberService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    @Override
    public List<ProjectMemberResponse> getAllMembers(Long projectId, Long id) {
        return List.of();
    }

    @Override
    public ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        return null;
    }

    @Override
    public ProjectMemberResponse updateMemberRole(Long userId, Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        return null;
    }

    @Override
    public void deleteMember(Long projectId, Long memberId) {

    }
}
