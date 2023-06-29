package com.sparta.spartablog.dto;

import com.sparta.spartablog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
    }
}
