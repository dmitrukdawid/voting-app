package com.example.demo.dto;

public record VoterDto(
    Long id,
    String name,
    boolean isBlocked
) {}

