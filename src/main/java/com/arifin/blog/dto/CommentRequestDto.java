package com.arifin.blog.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class CommentRequestDto {
    private Integer id;
    private String postSlug;
    private String name;
    private String email;
    private String body;
    private Instant createdAt;
}
