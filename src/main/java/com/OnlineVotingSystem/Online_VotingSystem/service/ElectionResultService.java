package com.OnlineVotingSystem.Online_VotingSystem.service;

import com.OnlineVotingSystem.Online_VotingSystem.entity.Candidate;
import com.OnlineVotingSystem.Online_VotingSystem.entity.ElectionResult;
import com.OnlineVotingSystem.Online_VotingSystem.exception.ResourceNotFoundException;
import com.OnlineVotingSystem.Online_VotingSystem.repository.CandidateRepository;
import com.OnlineVotingSystem.Online_VotingSystem.repository.ElectionResultRepository;
import com.OnlineVotingSystem.Online_VotingSystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionResultService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ElectionResultRepository electionResultRepository;
    @Autowired
    private VoteRepository voteRepository;

    public ElectionResult declareElectionResult(String electionName){
        Optional<ElectionResult> existingResult=this.electionResultRepository.findByElectionName(electionName);
        if(existingResult.isPresent()) {
            return  existingResult.get();
        }

        if(voteRepository.count()==0) {
            throw new IllegalStateException("Cannotdeclare the result as no votes have been cased!");
        }
        List<Candidate> allCandidates=candidateRepository.findAllByOrderByVoteCountDesc();
        if(allCandidates.isEmpty()) {
            throw new ResourceNotFoundException("No candidates available");
        }

        Candidate winner=allCandidates.get(0);
        int totalVotes=0;
        for(Candidate candidate:allCandidates) {
            totalVotes+=candidate.getVoteCount();
        }
        ElectionResult result=new ElectionResult();
        result.setElectionName(electionName);
        result.setWinner(winner);
        result.setTotalVotes(totalVotes);

        return electionResultRepository.save(result);
    }

    public List<ElectionResult> getAllResults(){
        return electionResultRepository.findAll();
    }
}
