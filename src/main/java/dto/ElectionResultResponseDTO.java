package dto;

public class ElectionResultResponseDTO {

    private String electionName;
    private int totalVotes;
    private long winnerId;
    private int winnerVotes;

    public String getElectionName() {
        return electionName;
    }
    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }
    public int getTotalVotes() {
        return totalVotes;
    }
    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
    public long getWinnerId() {
        return winnerId;
    }
    public void setWinnerId(long winnerId) {
        this.winnerId = winnerId;
    }
    public int getWinnerVotes() {
        return winnerVotes;
    }
    public void setWinnerVotes(int winnerVotes) {
        this.winnerVotes = winnerVotes;
    }
}
