package com.whiletrue.demo.service.impl;

import com.whiletrue.demo.dto.UserRegistrationRequestDto;
import com.whiletrue.demo.dto.UserRegistrationResponseDto;
import com.whiletrue.demo.exeption.EntityNotFoundException;
import com.whiletrue.demo.exeption.RegistrationException;
import com.whiletrue.demo.mapper.UserMapper;
import com.whiletrue.demo.model.Role;
import com.whiletrue.demo.model.RoleName;
import com.whiletrue.demo.model.User;
import com.whiletrue.demo.repository.RoleRepository;
import com.whiletrue.demo.repository.UserRepository;
import com.whiletrue.demo.service.UserService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserRegistrationResponseDto registration(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration.");
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setAddress(requestDto.getAddress());
        user.setPhone(requestDto.getPhone());
        user.setPassword(requestDto.getPassword());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(
                () -> new EntityNotFoundException("Can't find such role: " + RoleName.ROLE_USER));
        roles.add(userRole);

        user.setRoles(roles);

        return userMapper.toDto(userRepository.save(user));
    }
}
