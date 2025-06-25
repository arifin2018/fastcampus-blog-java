package com.arifin.blog.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.arifin.blog.entity.posts.Post;


@Repository
public interface PostRepository extends CrudRepository<Post,Integer>,PagingAndSortingRepository<Post,Integer> {
    Iterable<Post> findByIsDeleted(boolean isDeleted);
    Optional<Post> findBySlug(String slug);
    Optional<Post> findByIdAndIsDeleted(Integer id,boolean isDeleted);
    Optional<Post> findBySlugAndIsDeleted(String slug, boolean isDeleted);
}
