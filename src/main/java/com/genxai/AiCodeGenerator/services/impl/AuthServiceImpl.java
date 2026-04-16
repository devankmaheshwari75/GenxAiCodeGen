package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.config.AuthUtil;
import com.genxai.AiCodeGenerator.dtos.auth.AuthResponse;
import com.genxai.AiCodeGenerator.dtos.auth.SigninRequest;
import com.genxai.AiCodeGenerator.dtos.auth.SignupRequest;
import com.genxai.AiCodeGenerator.entities.User;
import com.genxai.AiCodeGenerator.errors.BadRequestException;
import com.genxai.AiCodeGenerator.mapper.UserMapper;
import com.genxai.AiCodeGenerator.repositories.UserRepository;
import com.genxai.AiCodeGenerator.services.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil authUtil;
    AuthenticationManager authenticationManager;





    @Override
    public AuthResponse signup(SignupRequest signupRequest) {

        userRepository.findByUsername(signupRequest.username()).ifPresent(user -> {
            throw new BadRequestException("User already exits with username" + signupRequest.username());

        });

        System.out.println(signupRequest.name() + signupRequest.username() +signupRequest.password());

        User user = userMapper.toUserEntity(signupRequest);

        user.setPassword(passwordEncoder.encode(signupRequest.password()));

        user  = userRepository.save(user);

        String token = authUtil.generateAccessToken(user);


        return new AuthResponse(token
                , userMapper.userToUserProfileResponse(user) );

    }

    @Override
    public AuthResponse login( SigninRequest signinRequest) {
        System.out.println("entered in the login flow ");

        Authentication authentication    = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.username() , signinRequest.password()));

        User user = (User) authentication.getPrincipal();

        if(user == null) {
            throw new RuntimeException("User is empty");

        }

        System.out.println(user);
        String token = authUtil.generateAccessToken(user);

        return new AuthResponse(token , userMapper.userToUserProfileResponse(user));





    }

    @Override
    public Object getProfile() {


        return null;
    }
}
