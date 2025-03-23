package com.whiletrue.demo.controller;

import com.whiletrue.demo.dto.UserLoginRequestDto;
import com.whiletrue.demo.dto.UserLoginResponseDto;
import com.whiletrue.demo.dto.UserRegistrationRequestDto;
import com.whiletrue.demo.dto.UserRegistrationResponseDto;
import com.whiletrue.demo.security.AuthService;
import com.whiletrue.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth management", description = "Endpoints for registration and login")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "Registration a new user", description = "Return registered user",
            security = @SecurityRequirement(name = "none"))
    @PostMapping("/register")
    public UserRegistrationResponseDto register(
            @Valid @RequestBody UserRegistrationRequestDto request) {
        return userService.registration(request);
    }

    @Operation(summary = "Login", description = "Return token",
            security = @SecurityRequirement(name = "none"))
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authService.authenticate(request);
    }
}
