package com.sparta.spartablog.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartablog.dto.CommonResponseDto;
import com.sparta.spartablog.exception.jwtTokenNotAvailableException;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j(topic = "AuthFilter")
@Component
@Order(1)
public class AuthFilterException extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
        } catch (jwtTokenNotAvailableException e) {
            filterException(response, e.getMessage());
        }
    }

    private void filterException(HttpServletResponse response, String message) {
        ObjectMapper objectMapper = new ObjectMapper();

//        Map<String, Object> errorDetails = new HashMap<>();
//        errorDetails.put("statusCode:", HttpStatus.BAD_REQUEST.value());
//        errorDetails.put("message", message);

        CommonResponseDto responseDto = new CommonResponseDto(HttpStatus.BAD_REQUEST.value(),message);

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try {
            objectMapper.writeValue(response.getWriter(), responseDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}