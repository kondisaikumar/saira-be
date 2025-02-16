package com.saira.sairarestful.service;

import com.saira.sairarestful.dtos.UserDto;
import com.saira.sairarestful.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAllAsDto() {
        return userRepository
                .findAll()
                .stream()
                .map(user ->
                        new UserDto(user.getId(), user.getFullName(), user.getEmail())
                )
                .collect(Collectors.toList());
    }

    public Optional<UserDto> findByIdAsDto(Long id) {
        return userRepository
                .findById(id)
                .map(user ->
                        new UserDto(user.getId(), user.getFullName(), user.getEmail())
                );
    }
}
