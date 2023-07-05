package com.sparta.spartablog.dto;

import lombok.Getter;

@Getter
public class CommonResponseDto {
    private Integer statusCode;
    private String msg;

    public CommonResponseDto(Integer statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
