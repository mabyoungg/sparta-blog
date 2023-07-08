package com.sparta.spartablog.entity;

import com.sparta.spartablog.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")

public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "contents")
    private String contents;

    public Comment(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
