package com.genxai.AiCodeGenerator.controllers;

import com.genxai.AiCodeGenerator.dtos.auth.*;
import com.genxai.AiCodeGenerator.services.AuthService;
import com.genxai.AiCodeGenerator.services.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    private final UserService userService;



    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup (@RequestBody @Valid SignupRequest signupRequest){

        return ResponseEntity.ok(authService.signup(signupRequest));


    }

    @PostMapping("/login")

    public ResponseEntity<AuthResponse> login(@RequestBody @Valid SigninRequest signinRequest){
        return ResponseEntity.ok(authService.login(signinRequest));

    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile( ){

        Long userId  = 1L;


        return ResponseEntity.ok(userService.getProfile(userId));

    }


}
