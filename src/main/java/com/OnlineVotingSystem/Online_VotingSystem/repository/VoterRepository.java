package com.OnlineVotingSystem.Online_VotingSystem.repository;

import com.OnlineVotingSystem.Online_VotingSystem.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    boolean existsByEmail(String email);
}
