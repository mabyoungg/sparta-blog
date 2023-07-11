package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.CommentRequestDto;
import com.sparta.spartablog.dto.CommentResponseDto;
import com.sparta.spartablog.dto.PostResponseDto;
import com.sparta.spartablog.entity.Comment;
import com.sparta.spartablog.entity.Post;
import com.sparta.spartablog.entity.User;
import com.sparta.spartablog.entity.UserRoleEnum;
import com.sparta.spartablog.exception.PermissionException;
import com.sparta.spartablog.repository.CommentRepository;
import com.sparta.spartablog.repository.PostRepository;
import com.sparta.spartablog.security.UserDetailsImpl;
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
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Post post = findPost(postId);

        Comment comment = new Comment(requestDto, user, post);


        comment.setUsername(user.getUsername());
        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }


    @Transactional
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Comment getComment = findComment(commentId);
        User user = userDetails.getUser();

        findPost(postId);

        if (user.getRole().equals(UserRoleEnum.USER)) {
            checkUser(getComment.getUsername(), user.getUsername());
        }

        getComment.update(requestDto);
        return new CommentResponseDto(getComment);
    }

    public void deleteComment(Long postId, Long commentId, UserDetailsImpl userDetails) {
        Comment getComment = findComment(commentId);
        User user = userDetails.getUser();
        findPost(postId);

        if (user.getRole().equals(UserRoleEnum.USER)) {
            checkUser(getComment.getUsername(), user.getUsername());
        }

        commentRepository.delete(getComment);
    }


    private void checkUser (String commentUsername, String loginUsername) {
        if (!Objects.equals(commentUsername, loginUsername)) {
            throw new PermissionException("작성자만 삭제/수정할 수 있습니다.");
        }
    }

    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(()->new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }


}
