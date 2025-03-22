package com.whiletrue.demo.service;

import com.whiletrue.demo.dto.UserRegistrationRequestDto;
import com.whiletrue.demo.dto.UserRegistrationResponseDto;

public interface UserService {
    UserRegistrationResponseDto registration(UserRegistrationRequestDto requestDto);
}
