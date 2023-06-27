package com.sparta.spartablog.dto;

import com.sparta.spartablog.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    public BlogResponseDto(Blog blog) {
        this.username = blog.getUsername();
        this.title = blog.getTitle();
        this.contents = blog.getContents();
        this.createdAt = blog.getCreatedAt();
    }
}
