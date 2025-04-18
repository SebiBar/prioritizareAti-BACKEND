package com.prioritizareAti.prioritizareAti.Pacient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Pacient {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificare
    private String nume;

/*    @Min(1)
    @Max(30)
    private int nrPat;*/

    // COMA
    private int gcs; // Glasgow Coma Scale (3–15)
    private int scorComa;

    // Cardiac
    private int tensiune;
    private int frecventaCardiaca; // bpm
    private boolean areVasopresoare;
    private boolean instabilHemodinamic;
    private int scorCardiac;

    // Respirator
    private String tipRespiratie; // "spontana", "asistata", "ventilatie"
    private int spo2; // Saturatia oxigenului (%)
    private boolean oxigenStabil;
    private int scorRespirator;

    // Infectios
    private boolean areInfectie;
    private boolean esteContagios;
    private boolean infectieControlata;
    private int scorInfectios;

    // Scoruri suplimentare
    private int sofa;
    private int apacheII;
    private double lactat; // mmol/L

    //Scoruri finale
    private int scorGeneralMedian;
    private int scorGeneralMinim;
    private int scorGeneralFinal;

    private void calculeazaScorComa() {
        // Transforma GCS (3-15) într-un scor de 1-10
        int minGCS = 3;
        int maxGCS = 15;

        this.scorComa = (int) Math.round(1 + ((double)(gcs - minGCS) / (maxGCS - minGCS)) * 9);
    }

    private void calculeazaScorCardiac() {
        if (instabilHemodinamic && areVasopresoare) {
            this.scorCardiac = 1;
        } else if (!areVasopresoare) {
            this.scorCardiac = 10;
        } else {
            this.scorCardiac = 5;
        }
    }

    private void calculeazaScorRespirator() {
        if (tipRespiratie.equals("ventilatie") && !oxigenStabil) {
            this.scorRespirator = 1;
        } else if (tipRespiratie.equals("spontana") && oxigenStabil) {
            this.scorRespirator = 10;
        } else {
            this.scorRespirator = 5;
        }
    }

    private void calculeazaScorInfectios() {
        if (esteContagios) {
            this.scorInfectios = 1;
        } else if (infectieControlata) {
            this.scorInfectios = 10;
        } else {
            this.scorInfectios = 5;
        }
    }

    private void calculeazaScorMedian() {
        this.scorGeneralMedian = (scorComa + scorCardiac + scorRespirator + scorInfectios) / 4;
    }

    private void calculeazaScorMinim() {
        this.scorGeneralMinim = Math.min(
                Math.min(scorComa, scorCardiac),
                Math.min(scorRespirator, scorInfectios)
        );
    }

    private void calculeazaScorFinal() {
        calculeazaScorMedian();
        calculeazaScorMinim();

        // Determină limita maximă a tier-ului minimului
        int limita;
        if (scorGeneralMinim <= 3) {
            limita = 3;
        } else if (scorGeneralMinim <= 6) {
            limita = 6;
        } else {
            limita = 10;
        }

        // Scorul final este media, dar limitata la tier-ul minimului
        this.scorGeneralFinal = Math.min(scorGeneralMedian, limita);
    }

    public void calculeazaToateScorurile(){
        calculeazaScorCardiac();
        calculeazaScorComa();
        calculeazaScorRespirator();
        calculeazaScorInfectios();

        calculeazaScorFinal();
    }

    public Pacient(String nume, int gcs, int tensiune,
                   int frecventaCardiaca, boolean areVasopresoare, boolean instabilHemodinamic,
                   String tipRespiratie, int spo2, boolean oxigenStabil,
                   boolean areInfectie, boolean esteContagios, boolean infectieControlata,
                   int sofa, int apacheII, double lactat) {
        this.nume = nume;
        this.gcs = gcs;
        this.tensiune = tensiune;
        this.frecventaCardiaca = frecventaCardiaca;
        this.areVasopresoare = areVasopresoare;
        this.instabilHemodinamic = instabilHemodinamic;
        this.tipRespiratie = tipRespiratie;
        this.spo2 = spo2;
        this.oxigenStabil = oxigenStabil;
        this.areInfectie = areInfectie;
        this.esteContagios = esteContagios;
        this.infectieControlata = infectieControlata;
        this.sofa = sofa;
        this.apacheII = apacheII;
        this.lactat = lactat;

        calculeazaToateScorurile();
    }

    public void UpdatePacient(PacientUpdateDTO updatedPacient){
        setNume(updatedPacient.getNume());

        // COMA
        setGcs(updatedPacient.getGcs());

        // Cardiac
        setTensiune(updatedPacient.getTensiune());
        setFrecventaCardiaca(updatedPacient.getFrecventaCardiaca());
        setAreVasopresoare(updatedPacient.isAreVasopresoare());
        setInstabilHemodinamic(updatedPacient.isInstabilHemodinamic());

        // Respirator
        setTipRespiratie(updatedPacient.getTipRespiratie());
        setSpo2(updatedPacient.getSpo2());
        setOxigenStabil(updatedPacient.isOxigenStabil());

        // Infectios
        setAreInfectie(updatedPacient.isAreInfectie());
        setEsteContagios(updatedPacient.isEsteContagios());
        setInfectieControlata(updatedPacient.isInfectieControlata());

        // Scoruri suplimentare
        setSofa(updatedPacient.getSofa());
        setApacheII(updatedPacient.getApacheII());
        setLactat(updatedPacient.getLactat());

        calculeazaToateScorurile();
    }



}


