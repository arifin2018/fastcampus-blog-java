package com.arifin.blog.response.comments;

import java.time.Instant;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentResponse {
    private Integer id;
    private String name;
    private Post post;
    private String body;
    private Instant createdAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private String title;
        private String slug;
    }
}
