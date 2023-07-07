package com.sparta.spartablog.controller;

import com.sparta.spartablog.dto.CommonResponseDto;
import com.sparta.spartablog.dto.LoginRequestDto;
import com.sparta.spartablog.dto.SignRequestDto;
import com.sparta.spartablog.entity.UserRoleEnum;
import com.sparta.spartablog.jwt.JwtUtil;
import com.sparta.spartablog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<CommonResponseDto> signup(@RequestBody @Valid SignRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(201).body(new CommonResponseDto(HttpStatus.CREATED.value(),"회원가입 성공"));
    }

    @PostMapping("/user/login")
    public ResponseEntity<CommonResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        userService.login(requestDto, res);

        return ResponseEntity.ok().body(new CommonResponseDto(HttpStatus.CREATED.value(),"로그인 성공"));
    }
}
