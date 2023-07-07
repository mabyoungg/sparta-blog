package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.LoginRequestDto;
import com.sparta.spartablog.dto.SignRequestDto;
import com.sparta.spartablog.entity.User;
import com.sparta.spartablog.entity.UserRoleEnum;
import com.sparta.spartablog.jwt.JwtUtil;
import com.sparta.spartablog.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "testadmintoken";

    public void signup(SignRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        UserRoleEnum role = UserRoleEnum.USER;

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }


        if (requestDto.getAdminToken().length()>0) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 인증 토큰이 일치하지 않습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }


        User user = new User(username, password, role);
        userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다."));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        res.addHeader(JwtUtil.AUTHORIZATION_HEADER,jwtUtil.createToken(requestDto.getUsername(), user.getRole()));

    }
}
