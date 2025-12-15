package com.example.demo.service;

import com.example.demo.dto.CastVoteRequest;
import com.example.demo.model.Election;
import com.example.demo.model.Vote;
import com.example.demo.model.VoteOption;
import com.example.demo.model.Voter;
import com.example.demo.repository.ElectionRepository;
import com.example.demo.repository.VoteRepository;
import com.example.demo.repository.VoteOptionRepository;
import com.example.demo.repository.VoterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotingService {

    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final ElectionRepository electionRepository;
    private final VoteOptionRepository voteOptionRepository;

    public VotingService(VoteRepository voteRepository, 
                         VoterRepository voterRepository, 
                         ElectionRepository electionRepository, 
                         VoteOptionRepository voteOptionRepository) {
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository;
        this.electionRepository = electionRepository;
        this.voteOptionRepository = voteOptionRepository;
    }

    @Transactional
    public void castVote(CastVoteRequest request) {
        // walidacja głosującego
        Voter voter = voterRepository.findById(request.voterId())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wyborcy."));

        if (voter.isBlocked()) {
            throw new IllegalStateException("Twój profil wyborcy jest zablokowany.");
        }

        // walidacja wyborów
        Election election = electionRepository.findById(request.electionId())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono takich wyborów."));

        // walidacja czy oddano głos
        if (voteRepository.existsByVoterIdAndElectionId(voter.getId(), election.getId())) {
            throw new IllegalStateException("Już oddałeś głos w tych wyborach.");
        }

        // walidacja opcji
        VoteOption option = voteOptionRepository.findById(request.optionId())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono takiej opcji głosowania."));

        if (!option.getElection().getId().equals(election.getId())) {
            throw new IllegalArgumentException("Wybrana opcja nie należy do tych wyborów.");
        }

        Vote vote = new Vote(voter, election, option);
        voteRepository.save(vote);
    }
}
