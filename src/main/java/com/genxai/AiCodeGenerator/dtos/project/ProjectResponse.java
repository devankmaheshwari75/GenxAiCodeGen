package com.genxai.AiCodeGenerator.dtos.project;

import com.genxai.AiCodeGenerator.dtos.auth.UserProfileResponse;

import java.time.Instant;

public record ProjectResponse(
        Long id ,

        String name ,
        UserProfileResponse user,
        Instant createdAt,
        Instant updatedAt
) {
}
