package com.sparta.spartablog.exception;

import com.sparta.spartablog.dto.CommonResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({PermissionException.class})
    public ResponseEntity<CommonResponseDto> PermissionException(PermissionException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.ok().body(new CommonResponseDto(HttpStatus.BAD_REQUEST.value(),ex.getMessage()));
    }

    @ExceptionHandler({UsernameDuplicationException.class})
    public ResponseEntity<CommonResponseDto> UsernameDuplicationException(UsernameDuplicationException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.ok().body(new CommonResponseDto(HttpStatus.BAD_REQUEST.value(),ex.getMessage()));
    }



//    @ExceptionHandler({AuthFilterException.class})
//    public ResponseEntity<String> handleException(AuthFilterException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//    }
}
