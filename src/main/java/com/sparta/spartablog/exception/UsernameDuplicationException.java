package com.sparta.spartablog.exception;

public class UsernameDuplicationException extends RuntimeException {
    public UsernameDuplicationException(String message) {
        super(message);
    }
}
