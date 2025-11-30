package dto;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class VoteResponseDTO {

    private String message;
    private boolean success;
    private Long voterId;
    private Long candidateId;

    public VoteResponseDTO(String message, boolean success, Long voterId, Long candidateId) {
        this.message = message;
        this.success = success;
        this.voterId = voterId;
        this.candidateId = candidateId;
    }
    public VoteResponseDTO() {
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public Long getVoterId() {
        return voterId;
    }
    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }
    public Long getCandidateId() {
        return candidateId;
    }
    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}
