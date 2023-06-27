package com.sparta.spartablog.dto;

import com.sparta.spartablog.entity.Blog;
import lombok.Getter;

@Getter
public class BlogResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private String password;

    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.username = blog.getUsername();
        this.title = blog.getTitle();
        this.contents = blog.getContents();
        this.password = blog.getPassword();
    }


}
