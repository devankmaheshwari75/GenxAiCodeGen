package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.dtos.project.InviteMemberRequest;
import com.genxai.AiCodeGenerator.dtos.project.ProjectMemberResponse;
import com.genxai.AiCodeGenerator.dtos.project.UpdateMemberRoleRequest;
import com.genxai.AiCodeGenerator.entities.Project;
import com.genxai.AiCodeGenerator.entities.ProjectMember;
import com.genxai.AiCodeGenerator.entities.ProjectMemberId;
import com.genxai.AiCodeGenerator.entities.User;
import com.genxai.AiCodeGenerator.mapper.ProjectMemberMapper;
import com.genxai.AiCodeGenerator.repositories.ProjectMemberRepository;
import com.genxai.AiCodeGenerator.repositories.ProjectRepository;
import com.genxai.AiCodeGenerator.repositories.UserRepository;
import com.genxai.AiCodeGenerator.services.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    @Override
    public List<ProjectMemberResponse> getAllMembers(Long projectId, Long userId) {

        Project project = getAccesibleProjectById(projectId, userId);

        List<ProjectMemberResponse> memberResponsesList = new ArrayList<>();

        memberResponsesList.add(projectMemberMapper.userToProjectMemberResponse(project.getOwner()));



        List<ProjectMemberResponse> members =   projectMemberRepository.findByIdProjectId(projectId).stream().map(projectMemberMapper::projectMemberToProjectMemberResponse)
                .toList();
        memberResponsesList.addAll(members);



        return memberResponsesList;

    }

    @Override
    public ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {

        Project project  = getAccesibleProjectById(projectId , userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("User is not the owner of the project");


        }

        User invitee = userRepository.findByEmail(request.email()).orElseThrow();
        if(invitee.getId().equals(userId)){
            throw new RuntimeException("Cannot invite yourself");

        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId , invitee.getId());

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Cannot invite again");

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
    public void updateMemberRole(Long userId, Long projectId, Long memberId, UpdateMemberRoleRequest request) {

//        check userId is owner of the project
//        owner is not asking to change his role
//        check whether member is a part of the project of

//        update the role of the member

        Project project  = getAccesibleProjectById(projectId , userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("User is not the owner of the project");

        }

        if(userId.equals(memberId)){
         throw new RuntimeException("Owner cannot change his role");

        }

        if(!projectMemberRepository.existsByIdProjectIdAndIdUserId(projectId , memberId)){
            throw new RuntimeException("Given user is not a member of the project");

        }

        ProjectMember projectMember = projectMemberRepository.findByIdProjectIdAndIdUserId(projectId , memberId);

        projectMember.setProjectRole(request.role());

        projectMemberRepository.save(projectMember);



    }
    @Transactional
    @Override
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
