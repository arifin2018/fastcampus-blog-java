package com.arifin.blog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.arifin.blog.dto.CreatePostRequest;
import com.arifin.blog.entity.Post;
import com.arifin.blog.response.CreatePostResponse;

@Mapper
public interface PostMapper {
    PostMapper Instance = Mappers.getMapper(PostMapper.class); 

    Post map(CreatePostRequest request);

    @Mapping(source = "slug", target = "path")
    CreatePostResponse map(Post post);
}