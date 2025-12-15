package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateElectionRequest(
    @NotBlank(message = "Title cannot be blank")
    String title,

    @NotEmpty(message = "Options list cannot be empty")
    List<String> options
) {}
