package com.OnlineVotingSystem.Online_VotingSystem.controller;

import com.OnlineVotingSystem.Online_VotingSystem.entity.Candidate;
import com.OnlineVotingSystem.Online_VotingSystem.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/add")
    public ResponseEntity<Candidate> registerVoter(@RequestBody @Valid Candidate candidate) {
        Candidate savedCandidate = candidateService.addCandidate(candidate);
        return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidate() {
        List<Candidate> candidateList = candidateService.getAllCandidates();
        return new ResponseEntity<>(candidateList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Candidate candidate = this.candidateService.getCandidateById(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate updatedCandidate = this.candidateService.updateCandidate(id, candidate);
        return new ResponseEntity<Candidate>(updatedCandidate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return new ResponseEntity<String>("Candidate with ID:" + id + " deleted successfully", HttpStatus.OK);
    }
}
