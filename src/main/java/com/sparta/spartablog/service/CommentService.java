package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.CommentRequestDto;
import com.sparta.spartablog.dto.CommentResponseDto;
import com.sparta.spartablog.dto.PostResponseDto;
import com.sparta.spartablog.entity.Comment;
import com.sparta.spartablog.entity.User;
import com.sparta.spartablog.entity.UserRoleEnum;
import com.sparta.spartablog.repository.CommentRepository;
import com.sparta.spartablog.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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


    @Transactional
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, HttpServletRequest req) {
        Comment getComment = findComment(commentId);
        User user = (User) req.getAttribute("user");

        checkPost(postId);

        if (user.getRole().equals(UserRoleEnum.USER)) {
            checkUser(getComment.getUsername(), user.getUsername());
        }

        getComment.update(requestDto);
        return new CommentResponseDto(getComment);
    }


    private void checkUser (String commentUsername, String loginUsername) {
        if (!Objects.equals(commentUsername, loginUsername)) {
            throw new IllegalArgumentException("작성자가 아닙니다.");
        }
    }

    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(()->new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }

    private void checkPost(Long postId) {
        postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }
}
