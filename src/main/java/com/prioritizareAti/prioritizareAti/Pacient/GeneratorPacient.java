package com.prioritizareAti.prioritizareAti.Pacient;

import com.prioritizareAti.prioritizareAti.Pacient.Pacient;

import java.util.Random;

public class GeneratorPacient {

    public static Pacient genereazaPacientRandom() {
        Random rand = new Random();

        String[] firstNames = { "Andrei", "Maria", "Elena", "Ion", "Ana", "George", "Ioana", "Vlad", "Irina", "Radu"};
        String[] lastNames = {"Popescu", "Ionescu", "Georgescu", "Stan", "Dumitru", "Enache", "Marin", "Nistor", "Lazăr", "Mihăilescu"};



        String[] tipuriRespiratie = {"spontana", "asistata", "ventilatie"};

        String nume = firstNames[rand.nextInt(firstNames.length)] + " " + lastNames[rand.nextInt(lastNames.length)];
        int gcs = rand.nextInt(13) + 3; // 3 - 15
        int tensiune = rand.nextInt(20) + 80;
        int frecventaCardiaca = rand.nextInt(60) + 60; // 60 - 120
        boolean areVasopresoare = rand.nextBoolean();
        boolean instabilHemodinamic = rand.nextBoolean();
        String tipRespiratie = tipuriRespiratie[rand.nextInt(tipuriRespiratie.length)];
        int spo2 = rand.nextInt(10) + 90; // 90 - 99
        boolean oxigenStabil = rand.nextBoolean();
        boolean areInfectie = rand.nextBoolean();
        boolean esteContagios = areInfectie && rand.nextBoolean(); // doar daca areInfectie
        boolean infectieControlata = areInfectie && rand.nextBoolean(); // doar daca areInfectie
        int sofa = rand.nextInt(15); // 0 - 14
        int apacheII = rand.nextInt(35); // 0 - 34
        double lactat = Math.round((rand.nextDouble() * 6.0) * 10.0) / 10.0; // 0.0 - 6.0 mmol/L, rotunjit la 1 zecimal

        return new Pacient(nume, gcs, tensiune,
                frecventaCardiaca, areVasopresoare, instabilHemodinamic,
                tipRespiratie, spo2, oxigenStabil,
                areInfectie, esteContagios, infectieControlata,
                sofa, apacheII, lactat);
    }
}
