package com.arifin.blog.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arifin.blog.dto.CommentRequestDto;
import com.arifin.blog.entity.Comment;
import com.arifin.blog.entity.Post;
import com.arifin.blog.mapper.CommentMapper;
import com.arifin.blog.repository.CommentRepository;
import com.arifin.blog.repository.PostRepository;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<Comment> CommentService(String getSlug, Integer pageNo, Integer limit) {
        Post post = postRepository.findBySlugAndIsDeleted(getSlug, false).orElse(null);
        if (post == null) {
            return null;
        }
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        return commentRepository.findByPostId(post.getId(), pageRequest).stream().collect(Collectors.toList());
    }

    public Comment getComments(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public Comment createComment(CommentRequestDto commentDto) {
        Post post = postRepository.findBySlugAndIsDeleted(commentDto.getPostSlug(), false).orElse(null);
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        Comment comment = commentMapper.toEntity(commentDto);
        comment.setCreatedAt(Instant.now());
        comment.setPost(post);
        comment = commentRepository.save(comment);

        post.setCommentCount((long) comments.size() + 1);
        postRepository.save(post);

        return comment;
    }

}
