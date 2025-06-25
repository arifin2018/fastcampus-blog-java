package com.arifin.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arifin.blog.dto.posts.CreatePostRequest;
import com.arifin.blog.entity.posts.Post;
import com.arifin.blog.mapper.posts.PostMapper;
import com.arifin.blog.response.posts.CreatePostResponse;
import com.arifin.blog.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public CreatePostResponse getPostBySlug(@PathVariable String slug){
        return postService.getPostBySlug(slug);
    }

    @PostMapping
    public CreatePostResponse createPost(@Valid @RequestBody CreatePostRequest createPostRequest){
        Post post = PostMapper.Instance.map(createPostRequest);
        post.setCommentCount(0L);
        post.setSlug(createPostRequest.getSlug());

        post = postService.createPost(post);

        return PostMapper.Instance.map(post);
    }

    @PutMapping("/{slug}")
    public CreatePostResponse updatePostBySlug(@PathVariable String slug, @RequestBody CreatePostRequest createPostRequest){
        Post post = PostMapper.Instance.map(createPostRequest);
        post = postService.updatePostBySlug(slug, post);

        return CreatePostResponse.builder()
                .title(post.getTitle())
                .body(post.getBody())
                .path(post.getSlug())
                .build();
    }

    @DeleteMapping("/{id}")
    public Boolean deletePostById(@PathVariable int id){
        return postService.deletePostById(id);
    }

    @PostMapping("/{id}/publish")
    public Post PublishPost(@PathVariable int id){
        return postService.PublishPost(id);
    }

    @PostMapping("/publish/test")
    public Post PublishPostTest(@PathVariable int id){
        return postService.PublishPost(id);
    }
}
