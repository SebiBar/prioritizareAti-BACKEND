package com.prioritizareAti.prioritizareAti.Pacient;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PacientDetailsDTO {
    private Pacient pacient;
    private List<PacientScoreLogDTO> pacientScoreLogDTO;


    public PacientDetailsDTO(Pacient pacient, List<PacientScoreLogDTO> pacientScoreLog) {
        this.pacient = pacient;
        this.pacientScoreLogDTO = pacientScoreLog;
    }
}
