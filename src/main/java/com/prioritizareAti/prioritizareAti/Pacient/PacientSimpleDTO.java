package com.prioritizareAti.prioritizareAti.Pacient;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PacientSimpleDTO {
    private Long id;
    private String nume;
    private int scorGeneralFinal;

    public PacientSimpleDTO(Pacient p) {
        this.scorGeneralFinal = p.getScorGeneralFinal();
        this.nume = p.getNume();
        this.id = p.getId();
    }

}
