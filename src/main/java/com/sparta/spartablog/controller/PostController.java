package com.sparta.spartablog.controller;

import com.sparta.spartablog.dto.CommonResponseDto;
import com.sparta.spartablog.dto.PostRequestDto;
import com.sparta.spartablog.dto.PostResponseDto;
import com.sparta.spartablog.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest req) {
        return postService.createPost(requestDto, req);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest req) {
        return postService.updatePost(id, requestDto, req);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<CommonResponseDto> deletePost(@PathVariable Long id, HttpServletRequest req) {
        postService.deletePost(id, req);

        return  ResponseEntity.ok().body(new CommonResponseDto(HttpStatus.CREATED.value(),"게시글 삭제 성공"));
    }
}
