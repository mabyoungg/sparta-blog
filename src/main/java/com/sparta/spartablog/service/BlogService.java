package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.BlogRequestDto;
import com.sparta.spartablog.dto.BlogResponseDto;
import com.sparta.spartablog.entity.Blog;
import com.sparta.spartablog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.HTMLWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        Blog saveBlog = blogRepository.save(blog);
        BlogResponseDto blogResponseDto = new BlogResponseDto(saveBlog);
        return blogResponseDto;
    }

    public List<BlogResponseDto> getBlogs() {
        return blogRepository.findAllByOrderByCreatedAtDesc().stream().map(BlogResponseDto::new).toList();
    }

    public BlogResponseDto getBlogById(Long id) {
        Blog getBlog = findBlog(id);
//        Blog getBlog = blogRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        BlogResponseDto blogResponseDto = new BlogResponseDto(getBlog);
        return blogResponseDto;
    }

    @Transactional
    public BlogResponseDto updateBlog(Long id, BlogRequestDto requestDto) {
        Blog getBlog = findBlog(id);
        if (getBlog.getPassword().equals(requestDto.getPassword())) {
            getBlog.update(requestDto);
            BlogResponseDto blogResponseDto = new BlogResponseDto(getBlog);
            return blogResponseDto;
        } else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    public Map<String, Object> deleteBlog(Long id, BlogRequestDto requestDto ) {
        Blog getBlog = findBlog(id);
        Map<String, Object> response = new HashMap<>();
        if (getBlog.getPassword().equals(requestDto.getPassword())) {
            blogRepository.delete(getBlog);
            response.put("success", true);
            return response;
        } else {
            response.put("success", false);
            return response;
        }
    }

    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
    }

}
