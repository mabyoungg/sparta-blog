package com.sparta.spartablog.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "최소 4자 이상, 10자 이하의 알파벳 소문자, 숫자로 구성해야 합니다.")
    private String username;
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "최소 8자 이상, 15자 이하의 알파벳 대소문자, 숫자로 구성해야 합니다.")
    private String password;
}
