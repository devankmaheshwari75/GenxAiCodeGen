package com.genxai.AiCodeGenerator.mapper;

import com.genxai.AiCodeGenerator.dtos.project.ProjectMemberResponse;
import com.genxai.AiCodeGenerator.entities.*;
import com.genxai.AiCodeGenerator.repositories.ProjectMemberRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {


    @Mapping(target = "userId" , source = "id")
    @Mapping(target = "projectRole" , constant = "OWNER")
    ProjectMemberResponse userToProjectMemberResponse(User user);



    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "projectRole", source = "projectRole")
    @Mapping(target = "invitedAt", source = "invitedAt")
    ProjectMemberResponse projectMemberToProjectMemberResponse(ProjectMember projectMember);


}
