package com.example.demo.controller;

import com.example.demo.dto.CreateElectionRequest;
import com.example.demo.dto.ElectionDto;
import com.example.demo.service.ElectionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elections")
public class ElectionController {

    private final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @PostMapping
    public ResponseEntity<ElectionDto> createElection(@Valid @RequestBody CreateElectionRequest request) {
        ElectionDto createdElection = electionService.createElection(request);
        return new ResponseEntity<>(createdElection, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ElectionDto>> getAllElections() {
        return ResponseEntity.ok(electionService.getAllElections());
    }
}

