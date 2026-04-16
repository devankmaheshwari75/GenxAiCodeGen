package com.genxai.AiCodeGenerator.dtos.project;

import com.genxai.AiCodeGenerator.entities.ProjectRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(
        @NotBlank
        @Email
        String username ,

        @NotNull
        ProjectRole role
) {

}
