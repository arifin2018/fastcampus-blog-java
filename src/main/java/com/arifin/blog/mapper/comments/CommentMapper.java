package com.arifin.blog.mapper.comments;

import com.arifin.blog.dto.comments.CreateCommentRequest;
import com.arifin.blog.entity.comments.Comment;
import com.arifin.blog.response.comments.CreateCommentResponse;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CreateCommentRequest dto);

    CreateCommentResponse toResponse(Comment comment);
}