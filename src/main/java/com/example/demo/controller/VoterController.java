package com.example.demo.controller;

import com.example.demo.dto.CreateVoterRequest;
import com.example.demo.dto.VoterDto;
import com.example.demo.service.VoterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping
    public ResponseEntity<VoterDto> createVoter(@Valid @RequestBody CreateVoterRequest request) {
        VoterDto createdVoter = voterService.createVoter(request);
        return new ResponseEntity<>(createdVoter, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VoterDto>> getAllVoters() {
        return ResponseEntity.ok(voterService.getAllVoters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoterDto> getVoter(@PathVariable Long id) {
        return ResponseEntity.ok(voterService.getVoter(id));
    }

    @PatchMapping("/{id}/block")
    public ResponseEntity<VoterDto> blockVoter(@PathVariable Long id) {
        return ResponseEntity.ok(voterService.toggleVoterBlockStatus(id, true));
    }

    @PatchMapping("/{id}/unblock")
    public ResponseEntity<VoterDto> unblockVoter(@PathVariable Long id) {
        return ResponseEntity.ok(voterService.toggleVoterBlockStatus(id, false));
    }
}

