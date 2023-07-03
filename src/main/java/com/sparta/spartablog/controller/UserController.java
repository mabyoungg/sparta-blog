package com.sparta.spartablog.controller;

import com.sparta.spartablog.dto.LoginRequestDto;
import com.sparta.spartablog.dto.SignRequestDto;
import com.sparta.spartablog.dto.SignResponseDto;
import com.sparta.spartablog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity signup(@RequestBody @Valid SignRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        return userService.login(requestDto, res);
    }
}
