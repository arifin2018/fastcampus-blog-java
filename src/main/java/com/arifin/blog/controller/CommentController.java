package com.arifin.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arifin.blog.dto.CreateCommentRequest;
import com.arifin.blog.entity.Comment;
import com.arifin.blog.response.CreateCommentResponse;
import com.arifin.blog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<Comment> getComments(@RequestParam(required = false) String postSlug,@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer limit) {
        return commentService.CommentService(postSlug, pageNo, limit);
    }

    @GetMapping("/{id}")
    public Comment getComments(@PathVariable Integer id) {
        return commentService.getComments(id);
    }

    @PostMapping
    public CreateCommentResponse createComment(@Valid @RequestBody CreateCommentRequest comment) {
        return commentService.createComment(comment);
    }
}
