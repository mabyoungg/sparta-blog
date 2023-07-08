package com.sparta.spartablog.exception;

public class jwtTokenNotAvailableException extends RuntimeException {
    public jwtTokenNotAvailableException(String message) {
        super(message);
    }
}
