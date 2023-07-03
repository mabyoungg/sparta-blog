package com.sparta.spartablog.dto;

import lombok.Getter;

@Getter
public class CommonResponseDto {
    int statusCode;
    String msg;

    public CommonResponseDto(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
