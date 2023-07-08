package com.sparta.spartablog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.spartablog.entity.Comment;
import com.sparta.spartablog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PostResponseDto {
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commetList;

    public PostResponseDto(Post post) {
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.commetList = post.getCommentList().stream().sorted(Comparator.comparing(Comment::getCreatedAt).reversed()).map(CommentResponseDto::new).toList();
    }
}
