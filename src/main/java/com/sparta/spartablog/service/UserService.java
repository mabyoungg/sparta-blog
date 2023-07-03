package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.SignRequestDto;
import com.sparta.spartablog.dto.SignResponseDto;
import com.sparta.spartablog.entity.User;
import com.sparta.spartablog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignResponseDto signup(SignRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재");
        }

        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일 존재");
        }

        User user = new User(username, password, email);
        userRepository.save(user);

        return new SignResponseDto(user);
    }
}
