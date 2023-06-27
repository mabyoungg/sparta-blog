package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.BlogRequestDto;
import com.sparta.spartablog.dto.BlogResponseDto;
import com.sparta.spartablog.entity.Blog;
import com.sparta.spartablog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

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
            BlogResponseDto blogResponseDto = new BlogResponseDto(getBlog);
            return blogResponseDto;
        }
    }


    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
    }
}
