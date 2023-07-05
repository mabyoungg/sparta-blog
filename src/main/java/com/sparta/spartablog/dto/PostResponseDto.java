package com.sparta.spartablog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.spartablog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
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
