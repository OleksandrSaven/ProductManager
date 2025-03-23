package com.whiletrue.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotBlank
    @Email
    @Schema(description = "Admin login", example = "admin@example.com")
    private String email;
    @NotBlank
    @Size(min = 6)
    @Schema(description = "Admin password", example = "1234567890")
    private String password;
}
