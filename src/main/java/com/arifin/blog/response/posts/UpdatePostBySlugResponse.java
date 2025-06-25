package com.arifin.blog.response.posts;

import java.util.Locale.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostBySlugResponse {
    private String title;
    private String body;
    private String slug;
    private Category category;
}
