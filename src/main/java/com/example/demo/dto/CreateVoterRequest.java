package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateVoterRequest(
    @NotBlank(message = "Name cannot be blank")
    String name
) {}

