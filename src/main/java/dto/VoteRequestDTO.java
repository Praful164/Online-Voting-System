package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequestDTO {
    @NotNull(message = "voter ID is required")
    Long voterId;
    @NotNull(message = "candidate ID is required")
    Long candidateId;


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
