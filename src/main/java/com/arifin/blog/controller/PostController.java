package com.arifin.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arifin.blog.entity.Post;
import com.arifin.blog.service.PostService;

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
    public Post getPostBySlug(@PathVariable String slug){
        return postService.getPostBySlug(slug);
    }

    @PostMapping("/")
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @PutMapping("/{slug}")
    public Post updatePostBySlug(@PathVariable String slug, @RequestBody Post postBody){
        return postService.updatePostBySlug(slug, postBody);
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
        // ArrayList<Integer> cars = new ArrayList<>(Array);
        return postService.PublishPost(id);
    }
}
