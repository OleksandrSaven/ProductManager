package com.whiletrue.demo.controller;

import com.whiletrue.demo.dto.UserInfoDto;
import com.whiletrue.demo.model.User;
import com.whiletrue.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User management", description = "Endpoints for managing users")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Info about me",
            description = "Return information about current registered user")
    @GetMapping("/info")
    public UserInfoDto aboutMe(@AuthenticationPrincipal User user) {
        return userService.aboutMe(user);
    }

    @Operation(summary = "Admin endpoint to find user info by user id",
            description = "Return user info")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public UserInfoDto findInfo(@PathVariable Long id) {
        return userService.info(id);
    }

    @Operation(summary = "Delete user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
