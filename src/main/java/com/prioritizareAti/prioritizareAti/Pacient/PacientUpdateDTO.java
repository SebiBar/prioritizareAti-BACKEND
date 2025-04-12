package com.prioritizareAti.prioritizareAti.Pacient;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacientUpdateDTO {

    /// E FOLOSIT SI PENTRU ADDING

    // Identificare
    private String nume;

    // COMA
    private int gcs; // Glasgow Coma Scale (3–15)

    // Cardiac
    private int tensiune;
    private int frecventaCardiaca; // bpm
    private boolean areVasopresoare;
    private boolean instabilHemodinamic;

    // Respirator
    private String tipRespiratie; // "spontana", "asistata", "ventilatie"
    private int spo2; // Saturatia oxigenului (%)
    private boolean oxigenStabil;

    // Infectios
    private boolean areInfectie;
    private boolean esteContagios;
    private boolean infectieControlata;

    // Scoruri suplimentare
    private int sofa;
    private int apacheII;
    private double lactat; // mmol/L

    public PacientUpdateDTO(Pacient p) {
        this.nume = p.getNume();
        this.gcs = p.getGcs();
        this.tensiune = p.getTensiune();
        this.frecventaCardiaca = p.getFrecventaCardiaca();
        this.areVasopresoare = p.isAreVasopresoare();
        this.instabilHemodinamic = p.isInstabilHemodinamic();
        this.tipRespiratie = p.getTipRespiratie();
        this.spo2 = p.getSpo2();
        this.oxigenStabil = p.isOxigenStabil();
        this.areInfectie = p.isAreInfectie();
        this.esteContagios = p.isEsteContagios();
        this.infectieControlata = p.isInfectieControlata();
        this.sofa = p.getSofa();
        this.apacheII = p.getApacheII();
        this.lactat = p.getLactat();
    }

    public PacientUpdateDTO() {
        // Needed for JSON deserialization
    }
}
