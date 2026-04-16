package com.genxai.AiCodeGenerator.dtos.project;

import com.genxai.AiCodeGenerator.entities.ProjectRole;

public record UpdateMemberRoleRequest(

        String username ,
        ProjectRole role

) {
}
