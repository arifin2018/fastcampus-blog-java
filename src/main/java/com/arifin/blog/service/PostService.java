package com.arifin.blog.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.arifin.blog.entity.posts.Post;
import com.arifin.blog.exception.ApiException;
import com.arifin.blog.mapper.posts.PostMapper;
import com.arifin.blog.repository.PostRepository;
import com.arifin.blog.response.posts.CreatePostResponse;
import com.arifin.blog.response.posts.DeletePostByIdResponse;
import com.arifin.blog.response.posts.GetPostResponse;
import com.arifin.blog.response.posts.PublishPostResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<GetPostResponse> getPosts(){
        List<GetPostResponse> responses = new ArrayList<>();
        postRepository.findByIsDeleted(false).forEach(post -> responses.add(PostMapper.Instance.mapToGetPostResponse(post)));
        return responses;
    }

    public CreatePostResponse getPostBySlug(String slug){
        Post post =  postRepository.findBySlugAndIsDeleted(slug,false).orElseThrow(new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get() {
                return new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
            }
        });
        return PostMapper.Instance.mapToCreatePostResponse(post);
    }

    public Post createPost(Post post){
        post.setCreatedAt(Instant.now());
        return postRepository.save(post);
    }

    public Post updatePostBySlug(String slug, Post postBody){
        Post savedPost = postRepository.findBySlugAndIsDeleted(slug,false).orElseThrow(new Supplier<RuntimeException>() {
            public RuntimeException get(){
                return new ApiException("post not found", HttpStatus.NOT_FOUND);
            }
        });
        if (savedPost == null) {
            return null;
        }
        postBody.setId(savedPost.getId());
        return postRepository.save(postBody);
    }

    public DeletePostByIdResponse deletePostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        post.setDeleted(true);
        postRepository.save(post);
        return DeletePostByIdResponse.builder().id(id).slug(post.getSlug()).build();
    }

    public PublishPostResponse PublishPost(Integer id) {
        Post post = postRepository.findByIdAndIsDeleted(id, false).orElseThrow(
                () -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        post.setPublished(true);
        post.setPublishedAt(Instant.now());
        postRepository.save(post);
        return PublishPostResponse.builder().publishedAt(post.getPublishedAt()).build();
    }
}
