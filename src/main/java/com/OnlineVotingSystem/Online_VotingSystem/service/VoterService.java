package com.OnlineVotingSystem.Online_VotingSystem.service;
import com.OnlineVotingSystem.Online_VotingSystem.entity.Candidate;
import com.OnlineVotingSystem.Online_VotingSystem.entity.Vote;
import com.OnlineVotingSystem.Online_VotingSystem.entity.Voter;
import com.OnlineVotingSystem.Online_VotingSystem.exception.DuplicateResourceException;
import com.OnlineVotingSystem.Online_VotingSystem.exception.ResourceNotFoundException;
import com.OnlineVotingSystem.Online_VotingSystem.repository.CandidateRepository;
import com.OnlineVotingSystem.Online_VotingSystem.repository.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    //New Voter Regestration
    public Voter registerVoter(Voter voter)
    {
        if(voterRepository.existsByEmail(voter.getEmail())){
            throw new DuplicateResourceException("Voter With Email Id "+voter.getEmail()+"Already Exists");
        }
        return voterRepository.save(voter);
    }

    //Get All Voter
    public List<Voter> getAllVoters()
    {
        return voterRepository.findAll();
    }

    //Get Voter By Id
    public Voter getVoterById(Long id)
    {
        Voter voter = voterRepository.findById(id).orElse(null);
        if(voter==null)
        {
            throw new ResourceNotFoundException("Voter With Id:"+id+"Not Found");
        }
        return voter;
    }

    //Voter Update BY ID
    public Voter updateVoter(Long id,Voter updatedVoter)
    {
        Voter voter = voterRepository.findById(id).orElse(null);
        if(voter==null)
        {
            throw new ResourceNotFoundException("Voter With Id:"+id+"Not Found");
        }
        if(updatedVoter.getName()!=null)
        {
            voter.setName(updatedVoter.getName());
        }
        if(updatedVoter.getEmail()!=null)
        {
            voter.setEmail(updatedVoter.getEmail());
        }
        return voterRepository.save(voter);
    }

    //Delete Voter By Id
    @Transactional
    public void deleteVoter(Long id)
    {
        Voter voter = voterRepository.findById(id).orElse(null);
        if(voter==null)
        {
            throw new ResourceNotFoundException("Cannot Delete Voter:"+id+"Because Voter Not Exists");
        }

        Vote vote = voter.getVote();
        if(vote!=null)
        {
            Candidate candidate = vote.getCandidate();
            candidate.setVoteCount(candidate.getVoteCount()-1);
            candidateRepository.save(candidate);
        }
        voterRepository.delete(voter);
    }
}
