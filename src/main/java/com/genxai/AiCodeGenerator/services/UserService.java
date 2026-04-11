package com.genxai.AiCodeGenerator.services;

import com.genxai.AiCodeGenerator.dtos.auth.UserProfileResponse;


public interface UserService {
    public UserProfileResponse getProfile(Long userId) ;
}
