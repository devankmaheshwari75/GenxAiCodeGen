package com.genxai.AiCodeGenerator.dtos.project;

import com.genxai.AiCodeGenerator.dtos.auth.UserProfileResponse;

import java.time.Instant;

public record ProjectResponse(
        Long id ,

        String name ,
        Instant createdAt,
        Instant updatedAt
) {
}
