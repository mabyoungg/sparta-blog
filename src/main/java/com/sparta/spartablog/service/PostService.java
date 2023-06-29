package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.PostRequestDto;
import com.sparta.spartablog.dto.PostResponseDto;
import com.sparta.spartablog.entity.Post;
import com.sparta.spartablog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
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
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post getPost = findPost(id);
        if (getPost.getPassword().equals(requestDto.getPassword())) {
            getPost.update(requestDto);
            return new PostResponseDto(getPost);
        } else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    public Map<String, Object> deletePost(Long id, PostRequestDto requestDto ) {
        Post getPost = findPost(id);
        Map<String, Object> response = new HashMap<>();
        if (getPost.getPassword().equals(requestDto.getPassword())) {
            postRepository.delete(getPost);
            response.put("success", true);
            return response;
        } else {
            response.put("success", false);
            return response;
        }
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
    }

}
