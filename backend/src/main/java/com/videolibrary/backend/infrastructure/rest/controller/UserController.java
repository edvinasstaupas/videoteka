package com.videolibrary.backend.infrastructure.rest.controller;

import com.videolibrary.backend.domain.entity.User;
import com.videolibrary.backend.domain.service.UserService;
import com.videolibrary.backend.infrastructure.rest.convert.UserMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateUpdateUserDto;
import com.videolibrary.backend.infrastructure.rest.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping
    public UserDto createUser(@RequestBody CreateUpdateUserDto dto) {
        User user = userService.createUser(dto);
        return userMapper.map(user);
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser() {
        User user = userService.getUser();
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.map(user));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody CreateUpdateUserDto dto) {
        User user = userService.updateUser(dto);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.map(user));
    }
}
