package com.arifin.blog.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.arifin.blog.entity.posts.Post;
import com.arifin.blog.exception.ApiException;
import com.arifin.blog.exception.ApiHandler;
import com.arifin.blog.mapper.posts.PostMapper;
import com.arifin.blog.repository.PostRepository;
import com.arifin.blog.response.posts.CreatePostResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts(){
        List<Post> result = new ArrayList<Post>();
        postRepository.findAll().forEach(result::add);
        return result;
    }

    public CreatePostResponse getPostBySlug(String slug){
        // Post post =  postRepository.findBySlugAndIsDeleted(slug,false).orElseThrow(new Supplier<RuntimeException>() {
        //     @Override
        //     public RuntimeException get() {
        //         return new ApiException("Not Found", HttpStatus.NOT_FOUND);
        //     }
        // });
        Post post =  postRepository.findBySlugAndIsDeleted(slug,false).orElseThrow(new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get() {
                return new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
            }
        });
        return PostMapper.Instance.map(post);
    }

    public Post createPost(Post post){
        post.setCreatedAt(Instant.now());
        return postRepository.save(post);
    }

    public Post updatePostBySlug(String slug, Post postBody){
        Post savedPost = postRepository.findBySlugAndIsDeleted(slug,false).orElse(null);
        if (savedPost == null) {
            return null;
        }
        postBody.setId(savedPost.getId());
        return postRepository.save(postBody);
    }

    public Boolean deletePostById(int id){
        Post savedPost = postRepository.findById(id).orElse(null);
        if (savedPost == null) {
            return null;
        }
        savedPost.setDeleted(true);
        postRepository.deleteById(id);
        return true;
    }

    public Post PublishPost(int id){
        Post savedPost = postRepository.findById(id).orElse(null);
        if (savedPost == null) {
            return null;
        }
        savedPost.setPublished(true);
        savedPost.setPublishedAt(Instant.now());
        return postRepository.save(savedPost);
    }
}
