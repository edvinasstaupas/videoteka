package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.User;
import com.videolibrary.backend.infrastructure.rest.convert.UserMapper;
import com.videolibrary.backend.infrastructure.rest.dto.CreateUpdateUserDto;
import com.videolibrary.backend.infrastructure.sql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthService authService;

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public User createUser(CreateUpdateUserDto dto) {
        User user = userMapper.map(dto);
        user.setId(authService.GetCurrentUserId());
        userRepository.save(user);
        return user;
    }

    public User getUser() {
        String userId = authService.GetCurrentUserId();
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(CreateUpdateUserDto dto) {
        String userId = authService.GetCurrentUserId();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        user.setUsername(dto.getUsername());
        userRepository.save(user);
        return user;
    }
}
