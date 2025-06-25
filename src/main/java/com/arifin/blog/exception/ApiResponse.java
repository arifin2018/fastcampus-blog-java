package com.arifin.blog.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private List<String> ErrorMessage; // Changed from ErrorMessage to ErrorMessage2ndary
}
