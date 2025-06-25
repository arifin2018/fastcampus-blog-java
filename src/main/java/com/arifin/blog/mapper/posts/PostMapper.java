package com.arifin.blog.mapper.posts;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.arifin.blog.dto.posts.CreatePostRequest;
import com.arifin.blog.entity.posts.Post;
import com.arifin.blog.response.posts.CreatePostResponse;
import com.arifin.blog.response.posts.GetPostResponse;

@Mapper
public interface PostMapper {
    PostMapper Instance = Mappers.getMapper(PostMapper.class); 

    Post mapToCreatePostResponse(CreatePostRequest postRequest);

    CreatePostResponse mapToCreatePostResponse(Post post);

    GetPostResponse mapToGetPostResponse(Post post);
}