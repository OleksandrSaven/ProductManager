package com.whiletrue.demo.dto;

import com.whiletrue.demo.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch(field = "password", fieldMatch = "repeatPassword",
        message = "The password and repeatPassword must match")
public class UserRegistrationRequestDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 255)
    private String password;
    @NotBlank
    @Size(min = 6, max = 255)
    private String repeatPassword;
    private String phone;
    private String address;
}
