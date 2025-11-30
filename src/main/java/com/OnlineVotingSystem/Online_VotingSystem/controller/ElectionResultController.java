package com.OnlineVotingSystem.Online_VotingSystem.controller;

import com.OnlineVotingSystem.Online_VotingSystem.entity.ElectionResult;
import com.OnlineVotingSystem.Online_VotingSystem.service.ElectionResultService;
import dto.ElectionResultRequestDTO;
import dto.ElectionResultResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/election-results")
@CrossOrigin
public class ElectionResultController {
    @Autowired
    private ElectionResultService electionResultService;

    @PostMapping("declare")
    public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody @Valid ElectionResultRequestDTO electionResult){
        ElectionResult result=this.electionResultService.declareElectionResult(electionResult.getElectionName());
        ElectionResultResponseDTO responseDTO=new ElectionResultResponseDTO();
        responseDTO.setElectionName(result.getElectionName());
        responseDTO.setTotalVotes(result.getTotalVotes());
        responseDTO.setWinnerId(result.getWinnerId());
        responseDTO.setWinnerVotes(result.getWinner().getVoteCount());
        return new ResponseEntity<ElectionResultResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ElectionResult>> getAllResults(){
        List<ElectionResult>results=this.electionResultService.getAllResults();
        return new ResponseEntity<List<ElectionResult>>(results,HttpStatus.OK);
    }
}
