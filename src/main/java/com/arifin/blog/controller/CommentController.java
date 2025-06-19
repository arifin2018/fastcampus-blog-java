package com.arifin.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arifin.blog.entity.Comment;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @GetMapping
    public List<Comment> getComments(@RequestParam(required = false) String postSlug,@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer limit) {
        return new ArrayList<Comment>();
    }

    @GetMapping("/{id}")
    public Comment getComments(@PathVariable Integer id) {
        return new Comment();
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return comment;
    }
}
