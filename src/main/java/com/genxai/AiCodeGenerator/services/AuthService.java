package com.genxai.AiCodeGenerator.services;

import com.genxai.AiCodeGenerator.dtos.auth.AuthResponse;
import com.genxai.AiCodeGenerator.dtos.auth.SigninRequest;
import com.genxai.AiCodeGenerator.dtos.auth.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public AuthResponse signup(SignupRequest signupRequest) ;

    public AuthResponse login(SigninRequest signinRequest);

    public Object getProfile() ;
}
