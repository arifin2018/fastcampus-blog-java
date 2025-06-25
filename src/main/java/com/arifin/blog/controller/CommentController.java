package com.arifin.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arifin.blog.dto.comments.CreateCommentRequest;
import com.arifin.blog.response.comments.CreateCommentResponse;
import com.arifin.blog.response.comments.GetCommentResponse;
import com.arifin.blog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<GetCommentResponse> getComments(@RequestParam(required = false) String postSlug,@RequestParam(required = false, defaultValue = "0") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return commentService.getComments(postSlug, pageNo, limit);
    }

    @GetMapping("/{id}")
    public CreateCommentResponse getComment(@PathVariable Integer id) {
        return commentService.getComment(id);
    }

    @PostMapping
    public CreateCommentResponse createComment(@Valid @RequestBody CreateCommentRequest comment) {
        return commentService.createComment(comment);
    }
}
