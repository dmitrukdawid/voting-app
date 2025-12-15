package com.example.demo.service;

import com.example.demo.dto.CreateVoterRequest;
import com.example.demo.dto.VoterDto;
import com.example.demo.model.Voter;
import com.example.demo.repository.VoterRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoterService {

    private final VoterRepository voterRepository;

    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @Transactional
    public VoterDto createVoter(CreateVoterRequest request) {
        Voter voter = new Voter(request.name());
        Voter savedVoter = voterRepository.save(voter);
        return mapToDto(savedVoter);
    }

    @Transactional(readOnly = true)
    public List<VoterDto> getAllVoters() {
        return voterRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public VoterDto getVoter(Long id) {
        Voter voter = voterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wyborcy o ID: " + id));
        return mapToDto(voter);
    }

    @Transactional
    public VoterDto toggleVoterBlockStatus(Long id, boolean block) {
        Voter voter = voterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wyborcy o ID: " + id));
        voter.setBlocked(block);
        return mapToDto(voter);
    }

    private VoterDto mapToDto(Voter voter) {
        return new VoterDto(voter.getId(), voter.getName(), voter.isBlocked());
    }


}

