package com.genxai.AiCodeGenerator.dtos.project;

import com.genxai.AiCodeGenerator.dtos.auth.UserProfileResponse;
import com.genxai.AiCodeGenerator.entities.ProjectRole;

import java.time.Instant;

public record ProjectMemberResponse (
        Long userId,
        String email ,
        String name ,

        ProjectRole projectRole,
        Instant invitedAt
){
}
