package com.arifin.blog.response.posts;

import java.time.Instant;
import java.util.Locale.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostResponse {
    private String title;
    private String body;
    private String slug;
    private Instant createdAt;
    private Long commentCount;
    private Category category;
}
