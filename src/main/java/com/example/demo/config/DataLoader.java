package com.example.demo.config;

import com.example.demo.model.Election;
import com.example.demo.model.VoteOption;
import com.example.demo.model.Voter;
import com.example.demo.repository.ElectionRepository;
import com.example.demo.repository.VoterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(ElectionRepository electionRepository, VoterRepository voterRepository) {
        return args -> {
            if (electionRepository.count() > 0) {
                return;
            }

            voterRepository.save(new Voter("Jan Kowalski"));
            voterRepository.save(new Voter("Anna Nowak"));
            voterRepository.save(new Voter("Piotr Wiśniewski"));

            Election election = new Election("Ulubiony język programowania");
            election.addOption(new VoteOption("Java"));
            election.addOption(new VoteOption("Python"));
            election.addOption(new VoteOption("C++"));
            electionRepository.save(election);
        };
    }
}

