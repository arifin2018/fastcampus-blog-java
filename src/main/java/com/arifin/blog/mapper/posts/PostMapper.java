package com.arifin.blog.mapper.posts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.arifin.blog.dto.posts.CreatePostRequest;
import com.arifin.blog.entity.posts.Post;
import com.arifin.blog.response.posts.CreatePostResponse;

@Mapper
public interface PostMapper {
    PostMapper Instance = Mappers.getMapper(PostMapper.class); 

    Post map(CreatePostRequest request);

    @Mapping(source = "slug", target = "path")
    CreatePostResponse map(Post post);
}