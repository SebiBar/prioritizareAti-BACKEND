package com.prioritizareAti.prioritizareAti.Pacient;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PacientScoreLogDTO {

    private int scorGeneralFinal;
    private LocalDateTime timestamp;

    public PacientScoreLogDTO(PacientScoreLog log) {
        this.scorGeneralFinal = log.getScorGeneralFinal();
        this.timestamp = log.getTimestamp();
    }
}