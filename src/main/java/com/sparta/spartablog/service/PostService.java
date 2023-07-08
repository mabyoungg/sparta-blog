package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.PostRequestDto;
import com.sparta.spartablog.dto.PostResponseDto;
import com.sparta.spartablog.entity.Post;
import com.sparta.spartablog.entity.User;
import com.sparta.spartablog.entity.UserRoleEnum;
import com.sparta.spartablog.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest req) {
        Post post = new Post(requestDto);
        User user = (User) req.getAttribute("user");

        post.setUsername(user.getUsername());

        Post savePost = postRepository.save(post);
        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPostById(Long id) {
        Post getPost = findPost(id);
        return new PostResponseDto(getPost);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest req) {
        Post getPost = findPost(id);
        User user = (User) req.getAttribute("user");

        if (user.getRole().equals(UserRoleEnum.USER)) {
            checkUser(getPost.getUsername(), user.getUsername());
        }

        getPost.update(requestDto);
        return new PostResponseDto(getPost);
    }

    public void deletePost(Long id, HttpServletRequest req) {
        Post getPost = findPost(id);
        User user = (User) req.getAttribute("user");

        if (user.getRole().equals(UserRoleEnum.USER)) {
            checkUser(getPost.getUsername(), user.getUsername());
        }

        postRepository.delete(getPost);
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
    }

    private void checkUser (String postUsername, String loginUsername) {
        if (!Objects.equals(postUsername, loginUsername)) {
            throw new IllegalArgumentException("작성자가 아닙니다.");
        }
    }


}
