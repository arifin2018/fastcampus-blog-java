package com.arifin.blog.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arifin.blog.entity.Post;
import com.arifin.blog.repository.PostRepository;

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

    public Post getPostBySlug(String slug){
        return postRepository.findBySlugAndIsDeleted(slug,false).orElse(null);
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
        System.out.println(savedPost.getCreatedAt());
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
