package com.example.demo.controller;

import com.example.demo.dto.CastVoteRequest;
import com.example.demo.service.VotingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VotingService votingService;

    public VoteController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PostMapping
    public ResponseEntity<Void> castVote(@Valid @RequestBody CastVoteRequest request) {
        votingService.castVote(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

