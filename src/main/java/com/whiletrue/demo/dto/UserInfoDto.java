package com.whiletrue.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;

}

