package com.arifin.blog.dto.comments;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCommentRequest {
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotNull
    @NotBlank
    private String name;

    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100, message = "Slug must be between 1 and 100 characters")
    private String slug;

    @Size(min = 1, max = 500, message = "Body must be between 1 and 500 characters")
    @NotNull
    @NotBlank
    private String body;
}
