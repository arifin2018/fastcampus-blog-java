package com.arifin.blog.mapper.comments;

import com.arifin.blog.dto.comments.CreateCommentRequest;
import com.arifin.blog.entity.comments.Comment;
import com.arifin.blog.response.comments.CreateCommentResponse;
import com.arifin.blog.response.comments.GetCommentResponse;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mappings({
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "post", ignore = true)
    })
    Comment toEntity(CreateCommentRequest dto);

    CreateCommentResponse toResponse(Comment comment);
    GetCommentResponse getCommentToResponse(Comment comment);
}