package com.OnlineVotingSystem.Online_VotingSystem.repository;

import com.OnlineVotingSystem.Online_VotingSystem.entity.ElectionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectionResultRepository extends JpaRepository<ElectionResult, Long> {

    Optional<ElectionResult> findByElectionName(String electionName);
}
