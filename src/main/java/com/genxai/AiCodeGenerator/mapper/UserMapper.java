package com.genxai.AiCodeGenerator.mapper;

import com.genxai.AiCodeGenerator.dtos.auth.SignupRequest;
import com.genxai.AiCodeGenerator.dtos.auth.UserProfileResponse;
import com.genxai.AiCodeGenerator.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface   UserMapper {

    User toUserEntity(SignupRequest signupRequest);

    UserProfileResponse userToUserProfileResponse(User user);

}
