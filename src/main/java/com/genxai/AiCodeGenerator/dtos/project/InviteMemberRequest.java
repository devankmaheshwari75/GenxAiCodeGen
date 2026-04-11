package com.genxai.AiCodeGenerator.dtos.project;

import com.genxai.AiCodeGenerator.entities.ProjectRole;

public record InviteMemberRequest(

        String email ,
        ProjectRole role
) {

}
