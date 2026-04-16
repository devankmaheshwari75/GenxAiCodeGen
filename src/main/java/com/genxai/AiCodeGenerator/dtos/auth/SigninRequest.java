package com.genxai.AiCodeGenerator.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SigninRequest(

        @NotBlank @Email String username,
        @Size(min = 4, max = 50) String password

) {
}
