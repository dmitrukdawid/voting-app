package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public record CastVoteRequest(
    @NotNull(message = "Voter ID cannot be null")
    Long voterId,

    @NotNull(message = "Election ID cannot be null")
    Long electionId,

    @NotNull(message = "Option ID cannot be null")
    Long optionId
) {}
