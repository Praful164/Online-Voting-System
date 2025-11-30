package com.OnlineVotingSystem.Online_VotingSystem.controller;

import com.OnlineVotingSystem.Online_VotingSystem.entity.Vote;
import com.OnlineVotingSystem.Online_VotingSystem.service.VotingService;
import dto.VoteRequestDTO;
import dto.VoteResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VotingController {

    @Autowired
    private VotingService votingService;

    @PostMapping("/cast")
    public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequest)
    {
         Vote vote = votingService.castVote(voteRequest.getVoterId(),voteRequest.getCandidateId());
         VoteResponseDTO voteResponseDTO = new VoteResponseDTO("Vote Casted Successfully",true,vote.getVoterId(),vote.getCandidateId());
         return new ResponseEntity<>(voteResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes()
    {
        List<Vote> voteList = votingService.getAllVotes();
        return new ResponseEntity<>(voteList,HttpStatus.OK);
    }
}
