package com.example.demo.service;

import com.example.demo.dto.CreateElectionRequest;
import com.example.demo.dto.ElectionDto;
import com.example.demo.dto.VoteOptionDto;
import com.example.demo.model.Election;
import com.example.demo.model.VoteOption;
import com.example.demo.repository.ElectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectionService {

    private final ElectionRepository electionRepository;

    public ElectionService(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    @Transactional
    public ElectionDto createElection(CreateElectionRequest request) {
        Election election = new Election(request.title());
        
        request.options().forEach(optionName -> {
            VoteOption option = new VoteOption(optionName);
            election.addOption(option);
        });

        Election savedElection = electionRepository.save(election);
        return mapToDto(savedElection);
    }

    @Transactional(readOnly = true)
    public List<ElectionDto> getAllElections() {
        return electionRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ElectionDto mapToDto(Election election) {
        List<VoteOptionDto> optionDtos = election.getOptions().stream()
                .map(o -> new VoteOptionDto(o.getId(), o.getName()))
                .collect(Collectors.toList());
        
        return new ElectionDto(election.getId(), election.getTitle(), optionDtos);
    }
}
