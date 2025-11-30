package com.OnlineVotingSystem.Online_VotingSystem.exception;

public class VoteNotAllowedException extends RuntimeException{

    public VoteNotAllowedException(String message)
    {
        super(message);
    }
}
