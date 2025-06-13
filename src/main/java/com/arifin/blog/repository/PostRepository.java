package com.arifin.blog.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arifin.blog.entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post,Integer> {
    Optional<Post> findBySlug(String slug);
    Optional<Post> findBySlugAndIsDeleted(String slug, boolean isDeleted);
}
