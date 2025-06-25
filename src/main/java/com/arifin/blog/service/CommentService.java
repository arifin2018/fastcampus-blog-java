package com.arifin.blog.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.arifin.blog.dto.comments.CreateCommentRequest;
import com.arifin.blog.entity.comments.Comment;
import com.arifin.blog.entity.posts.Post;
import com.arifin.blog.exception.ApiException;
import com.arifin.blog.repository.CommentRepository;
import com.arifin.blog.repository.PostRepository;
import com.arifin.blog.response.comments.CreateCommentResponse;
import com.arifin.blog.response.comments.GetCommentResponse;
import com.arifin.blog.response.posts.GetPostResponse;
import com.arifin.blog.mapper.comments.CommentMapper;
import com.arifin.blog.mapper.posts.PostMapper;

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

    public List<GetCommentResponse> getComments(String getSlug, Integer pageNo, Integer limit) {
        Post post = postRepository.findBySlugAndIsDeleted(getSlug, false).orElseThrow(() -> new ApiException("not found",HttpStatus.NOT_FOUND));
        if (post == null) {
            return null;
        }
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        List<GetCommentResponse> response =  new ArrayList<>();
        commentRepository.findByPostId(post.getId(), pageRequest).stream().collect(Collectors.toList()).forEach(comments -> response.add(commentMapper.getCommentToResponse(comments)));
        return response;
    }

    public CreateCommentResponse getComment(Integer id) {
        commentRepository.findById(id).orElseThrow(new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get(){
                return new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
            }
        });
        return new CreateCommentResponse();
    }
    
    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest commentDto) {
        Post post = postRepository.findBySlugAndIsDeleted(commentDto.getSlug(), false).orElseThrow(new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get(){
                // return new Run(HttpStatus.NOT_FOUND, "not found");
                return new ApiException("not found",HttpStatus.NOT_FOUND);
            }
        });
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        Comment comment = commentMapper.toEntity(commentDto);
        comment.setCreatedAt(Instant.now());
        comment.setPost(post);
        comment = commentRepository.save(comment);

        post.setCommentCount((long) comments.size() + 1);
        postRepository.save(post);

        return commentMapper.toResponse(comment);
    }

}
