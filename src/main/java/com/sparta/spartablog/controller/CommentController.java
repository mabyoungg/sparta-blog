package com.sparta.spartablog.controller;

import com.sparta.spartablog.dto.CommentRequestDto;
import com.sparta.spartablog.dto.CommentResponseDto;
import com.sparta.spartablog.dto.CommonResponseDto;
import com.sparta.spartablog.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public CommentResponseDto createComment (@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, HttpServletRequest req) {
        return commentService.createComment(postId, requestDto, req);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public CommentResponseDto updateComment (@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, HttpServletRequest req) {
        return commentService.updateComment(postId,commentId, requestDto, req);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommonResponseDto> deleteComment (@PathVariable Long postId, @PathVariable Long commentId, HttpServletRequest req) {
        commentService.deleteComment(postId,commentId, req);
        return  ResponseEntity.ok().body(new CommonResponseDto(HttpStatus.CREATED.value(),"댓글 삭제 성공"));
    }
}
