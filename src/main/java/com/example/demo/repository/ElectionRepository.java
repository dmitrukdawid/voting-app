package com.example.demo.repository;

import com.example.demo.model.Election;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

    @Override
    @EntityGraph(attributePaths = "options") // lub fetch join
    List<Election> findAll();
}
