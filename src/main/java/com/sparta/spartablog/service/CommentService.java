package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.CommentRequestDto;
import com.sparta.spartablog.dto.CommentResponseDto;
import com.sparta.spartablog.entity.Comment;
import com.sparta.spartablog.entity.User;
import com.sparta.spartablog.repository.CommentRepository;
import com.sparta.spartablog.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, HttpServletRequest req) {
        Comment comment = new Comment(requestDto);
        User user = (User) req.getAttribute("user");

        checkPost(postId);

        comment.setUsername(user.getUsername());
        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }

    private void checkPost(Long postId) {
        postRepository.findById(postId).orElseThrow(()->new NullPointerException("게시글이 존재하지 않습니다."));
    }
}
