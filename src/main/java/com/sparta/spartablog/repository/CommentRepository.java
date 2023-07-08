package com.sparta.spartablog.repository;

import com.sparta.spartablog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
