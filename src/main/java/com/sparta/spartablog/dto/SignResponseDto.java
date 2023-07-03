package com.sparta.spartablog.dto;

import com.sparta.spartablog.entity.User;
import lombok.Getter;

@Getter
public class SignResponseDto {
    private String username;

    public SignResponseDto(User user) {
        this.username = user.getUsername();
    }
}
