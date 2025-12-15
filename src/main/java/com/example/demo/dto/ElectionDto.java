package com.example.demo.dto;

import java.util.List;

public record ElectionDto(
    Long id,
    String title,
    List<VoteOptionDto> options
) {}
