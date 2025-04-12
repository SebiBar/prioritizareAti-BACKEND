package com.prioritizareAti.prioritizareAti.Pacient;

import com.prioritizareAti.prioritizareAti.Pacient.Pacient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PacientScoreLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacient;

    private LocalDateTime timestamp;

    private int scorComa;
    private int scorCardiac;
    private int scorRespirator;
    private int scorInfectios;
    private int scorGeneralFinal;

    // Constructors

    public PacientScoreLog() {
        this.timestamp = LocalDateTime.now();
    }

    public PacientScoreLog(Pacient pacient) {
        this.pacient = pacient;
        this.timestamp = LocalDateTime.now();
        this.scorComa = pacient.getScorComa();
        this.scorCardiac = pacient.getScorCardiac();
        this.scorRespirator = pacient.getScorRespirator();
        this.scorInfectios = pacient.getScorInfectios();
        this.scorGeneralFinal = pacient.getScorGeneralFinal();
    }

}
