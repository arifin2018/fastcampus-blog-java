package com.arifin.blog.dto;

import java.time.Instant;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {
    @Size(min = 3, message= "Title must be at least 3 characters long")
    @NotNull
    private String title;

    @Size(min = 10, message= "Body must be at least 10 characters long")
    @NotNull
    private String body;

    @Size(min = 6, message= "Slug must be at least 6 characters long")
    @NotNull
    private String slug;
}
