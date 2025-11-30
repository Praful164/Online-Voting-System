package com.OnlineVotingSystem.Online_VotingSystem.repository;

import com.OnlineVotingSystem.Online_VotingSystem.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findAllByOrderByVoteCountDesc();
}
