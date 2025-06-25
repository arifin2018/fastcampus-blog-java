package com.arifin.blog.mapper;

import com.arifin.blog.dto.CreateCommentRequest;
import com.arifin.blog.entity.Comment;
import com.arifin.blog.response.CreateCommentResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CreateCommentRequest dto);

    CreateCommentResponse toResponse(Comment comment);
}