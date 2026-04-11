package com.genxai.AiCodeGenerator.dtos.auth;

public record SignupRequest(

        String email ,
        String name ,
        String password
) {
}
