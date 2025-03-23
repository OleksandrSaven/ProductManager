package com.whiletrue.demo.service;

import com.whiletrue.demo.dto.UserInfoDto;
import com.whiletrue.demo.dto.UserRegistrationRequestDto;
import com.whiletrue.demo.dto.UserRegistrationResponseDto;
import com.whiletrue.demo.model.User;

public interface UserService {

    UserRegistrationResponseDto registration(UserRegistrationRequestDto requestDto);

    UserInfoDto aboutMe(User user);

    UserInfoDto info(Long id);

    void deleteUserById(Long id);

    User getAuthenticatedUser();

}
