package com.OnlineVotingSystem.Online_VotingSystem.repository;

import com.OnlineVotingSystem.Online_VotingSystem.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
