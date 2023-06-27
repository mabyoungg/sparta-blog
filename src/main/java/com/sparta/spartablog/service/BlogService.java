package com.sparta.spartablog.service;

import com.sparta.spartablog.dto.BlogRequestDto;
import com.sparta.spartablog.dto.BlogResponseDto;
import com.sparta.spartablog.entity.Blog;
import com.sparta.spartablog.repository.BlogRepository;
import org.springframework.stereotype.Service;

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
}
