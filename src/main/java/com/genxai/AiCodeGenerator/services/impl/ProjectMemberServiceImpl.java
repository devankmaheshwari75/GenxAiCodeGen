package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.config.AuthUtil;
import com.genxai.AiCodeGenerator.dtos.project.InviteMemberRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectMemberResponse;
import com.genxai.AiCodeGenerator.dtos.project.UpdateMemberRoleRequest;
import com.genxai.AiCodeGenerator.entities.Project;
import com.genxai.AiCodeGenerator.entities.ProjectMember;
import com.genxai.AiCodeGenerator.entities.ProjectMemberId;
import com.genxai.AiCodeGenerator.entities.User;
import com.genxai.AiCodeGenerator.errors.BadRequestException;
import com.genxai.AiCodeGenerator.mapper.ProjectMemberMapper;
import com.genxai.AiCodeGenerator.repositories.ProjectMemberRepository;
import com.genxai.AiCodeGenerator.repositories.ProjectRepository;
import com.genxai.AiCodeGenerator.repositories.UserRepository;
import com.genxai.AiCodeGenerator.services.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)



public class ProjectMemberServiceImpl implements ProjectMemberService {
    ProjectMemberRepository projectMemberRepository;
    UserRepository userRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    AuthUtil authUtil;


    @Override
    @PreAuthorize("@security.canViewMembers(#projectId)")
    public List<ProjectMemberResponse> getAllMembers(Long projectId) {
        Long userId = authUtil.getCurrentUserId();



        List<ProjectMemberResponse> members =   projectMemberRepository.findByIdProjectId(projectId).stream().map(projectMemberMapper::projectMemberToProjectMemberResponse)
                .toList();
        return new ArrayList<>(members);

    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request) {

        Long userId = authUtil.getCurrentUserId();

        Project project  = getAccesibleProjectById(projectId , userId);


        User invitee = userRepository.findByUsername(request.username()).orElseThrow();

        if(invitee.getId().equals(userId)){
            throw new BadRequestException("Cannot invite yourself");

        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId , invitee.getId());

        System.out.println("hi there after project member id ");

        if(projectMemberRepository.existsById(projectMemberId)){
            System.out.println("same projectmember id");
            throw new BadRequestException("Cannot invite again");

        }


        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();



        projectMemberRepository.save(member);
        return projectMemberMapper.projectMemberToProjectMemberResponse(member);


    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public ProjectMemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {

        Long userId = authUtil.getCurrentUserId();
        Project project  = getAccesibleProjectById(projectId , userId);

//         check whether owneer is not changing its own role

        User userToBeUpdated  = userRepository.findByUsername(request.username()).orElseThrow();

        if(userToBeUpdated.getId().equals(userId)){
            throw new BadRequestException("Owner cant change his own role");

        }

//        check whether the usertobeupdated is the part of the project or not

        ProjectMemberId id   = new ProjectMemberId(projectId , userToBeUpdated.getId());
        ProjectMember projectMember = projectMemberRepository.findById(id).orElseThrow( ()->  new BadRequestException("Member is not part of project"));



        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);

        return projectMemberMapper.projectMemberToProjectMemberResponse(projectMember);








    }
    @Transactional
    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public void deleteMember(Long projectId, Long memberId) {

        if(!projectMemberRepository.existsByIdProjectIdAndIdUserId(projectId , memberId)){
            throw new RuntimeException("user is not a member of the project");
        }
        projectMemberRepository.deleteByIdProjectIdAndUserId(projectId, memberId);

    }

    public Project getAccesibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
