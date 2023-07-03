package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.CommonResponseDto;
import com.sparta.spartablog.dto.LoginRequestDto;
import com.sparta.spartablog.dto.SignRequestDto;
import com.sparta.spartablog.entity.User;
import com.sparta.spartablog.jwt.JwtUtil;
import com.sparta.spartablog.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<CommonResponseDto> signup(SignRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재");
        }

        User user = new User(username, password);
        userRepository.save(user);

        CommonResponseDto commonResponseDto = new CommonResponseDto(HttpStatus.OK.value(), "회원 가입 성공");
        return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<CommonResponseDto> login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다."));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token, res);

        CommonResponseDto commonResponseDto = new CommonResponseDto(HttpStatus.OK.value(), "로그인 성공");
        return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
    }
}
