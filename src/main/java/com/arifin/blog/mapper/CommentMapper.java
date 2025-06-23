package com.arifin.blog.mapper;

import com.arifin.blog.dto.CommentRequestDto;
import com.arifin.blog.entity.Comment;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CommentRequestDto dto);
}