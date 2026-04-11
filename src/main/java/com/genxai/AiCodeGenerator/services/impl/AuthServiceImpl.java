package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.dtos.auth.AuthResponse;
import com.genxai.AiCodeGenerator.dtos.auth.SigninRequest;
import com.genxai.AiCodeGenerator.dtos.auth.SignupRequest;
import com.genxai.AiCodeGenerator.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest signupRequest) {
        return null;
    }

    @Override
    public AuthResponse login(SigninRequest signinRequest) {
        return null;
    }

    @Override
    public Object getProfile() {
        return null;
    }
}
