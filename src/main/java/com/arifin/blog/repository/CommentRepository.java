package com.arifin.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arifin.blog.entity.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Page<Comment> findByPostId(Integer postId, Pageable pageable);
    List<Comment> findByPostId(Integer postId);
}