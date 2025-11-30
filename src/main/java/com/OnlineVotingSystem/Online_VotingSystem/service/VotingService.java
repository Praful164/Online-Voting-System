package com.OnlineVotingSystem.Online_VotingSystem.service;

import com.OnlineVotingSystem.Online_VotingSystem.entity.Candidate;
import com.OnlineVotingSystem.Online_VotingSystem.entity.Vote;
import com.OnlineVotingSystem.Online_VotingSystem.entity.Voter;
import com.OnlineVotingSystem.Online_VotingSystem.exception.ResourceNotFoundException;
import com.OnlineVotingSystem.Online_VotingSystem.exception.VoteNotAllowedException;
import com.OnlineVotingSystem.Online_VotingSystem.repository.CandidateRepository;
import com.OnlineVotingSystem.Online_VotingSystem.repository.VoteRepository;
import com.OnlineVotingSystem.Online_VotingSystem.repository.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private VoterRepository voterRepository;

    public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository, VoterRepository voterRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }

    @Transactional
    public Vote castVote(Long voterId,Long candidateId)
    {
        if(!voterRepository.existsById(voterId)){
            throw new ResourceNotFoundException("Voter Not Found With Id:"+voterId);
        }
        if(!candidateRepository.existsById(candidateId)){
            throw new ResourceNotFoundException("Candidate Not Found With Id:"+candidateId);
        }
        Voter voter=voterRepository.findById(voterId).get();
        if(voter.isHasVoted()){
            throw new VoteNotAllowedException("Voter Id:"+voterId+"has already casted vote" );
        }
        Candidate candidate = candidateRepository.findById(candidateId).get();
        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setCandidate(candidate);
        voteRepository.save(vote);

        candidate.setVoteCount(candidate.getVoteCount()+1);
        candidateRepository.save(candidate);
        voter.setVote(vote);
        voter.setHasVoted(true);
        voterRepository.save(voter);

        return vote;
    }

    public List<Vote> getAllVotes(){
        return voteRepository.findAll();
    }
}
