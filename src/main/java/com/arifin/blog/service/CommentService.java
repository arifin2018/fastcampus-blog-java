package com.arifin.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.arifin.blog.entity.Comment;

@Service
public class CommentService {

    public List<Comment> CommentService(String getSlug, Integer pageNo, Integer limit) {
        return new ArrayList<Comment>();
    }

    public Comment getComments(Integer id) {
        return new Comment();
    }
    
    public Comment createComment(Comment comment) {
        return comment;
    }

}
