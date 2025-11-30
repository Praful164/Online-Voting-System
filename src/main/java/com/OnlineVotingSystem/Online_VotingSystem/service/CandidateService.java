package com.OnlineVotingSystem.Online_VotingSystem.service;

import com.OnlineVotingSystem.Online_VotingSystem.entity.Candidate;
import com.OnlineVotingSystem.Online_VotingSystem.entity.Vote;
import com.OnlineVotingSystem.Online_VotingSystem.exception.ResourceNotFoundException;
import com.OnlineVotingSystem.Online_VotingSystem.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    //Add new Candidate

    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    //Get All Candidate
    public List<Candidate> getAllCandidates(){
        return candidateRepository.findAll();
    }

    //Get Candidate by id
    public Candidate getCandidateById(Long id) {
        Candidate candidate=candidateRepository.findById(id).orElse(null);
        if(candidate==null) {
            throw new ResourceNotFoundException("Candidate with id: "+id+ " not found");
        }

        return candidate;
    }

    //Update Candidate
    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        Candidate candidate=getCandidateById(id);
        if(updatedCandidate.getName()!=null) {
            candidate.setName(updatedCandidate.getName());
        }
        if(updatedCandidate.getParty()!=null) {
            candidate.setParty(updatedCandidate.getParty());
        }

        return candidateRepository.save(candidate);
    }

    //Delete Candidate
    public void deleteCandidate(Long id) {
        Candidate candidate=getCandidateById(id);
        List<Vote>votes=candidate.getVote();
        for(Vote v:votes) {
            v.setCandidate(null);
        }
        candidate.getVote().clear();
        candidateRepository.delete(candidate);
    }
}
