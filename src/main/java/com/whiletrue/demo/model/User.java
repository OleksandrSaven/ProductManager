package com.whiletrue.demo.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private Set<Role> roles = new HashSet<>();
}
