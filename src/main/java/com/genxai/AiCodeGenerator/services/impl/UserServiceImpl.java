package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.dtos.auth.UserProfileResponse;

import com.genxai.AiCodeGenerator.errors.ResourceNotFound;
import com.genxai.AiCodeGenerator.repositories.UserRepository;
import com.genxai.AiCodeGenerator.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         return userRepository.findByUsername(username).orElseThrow();


    }
}
