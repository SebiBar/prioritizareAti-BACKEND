package com.prioritizareAti.prioritizareAti.Pacient;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacientService {

    final PacientRepository pacientRepository;
    final PacientScoreLogRepository pacientScoreLogRepository;

    @Autowired
    public PacientService(PacientRepository pacientRepository , PacientScoreLogRepository pacientScoreLogRepository) {
        this.pacientRepository = pacientRepository;
        this.pacientScoreLogRepository =  pacientScoreLogRepository;
    }


    public List<PacientSimpleDTO> getSimpleDTOList() {
        List<Pacient> pacienti = this.getPacientList();

        List<PacientSimpleDTO> dtoList = new ArrayList<>();
        for (Pacient p : pacienti) {
            dtoList.add(new PacientSimpleDTO(p));
        }
        return dtoList;
    }

    public List<Pacient> getPacientList() {
        return pacientRepository.findAll();
    }

    public Pacient getPacientById(Long id) {
        return pacientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nu s-a gasit pacientul cu id-ul " + id));
    }

    public PacientDetailsDTO getPacientDetails(Long id){
        Pacient pacient = this.getPacientById(id);
        List<PacientScoreLog> logs = pacientScoreLogRepository.findByPacient_Id(id);

        // convert to DTOs
        List<PacientScoreLogDTO> logDTOs = logs.stream()
                .map(PacientScoreLogDTO::new)
                .collect(Collectors.toList());

        return new PacientDetailsDTO(pacient, logDTOs);
    }

    public Pacient generatePacient(){
        Pacient p = GeneratorPacient.genereazaPacientRandom();
        pacientRepository.save(p);
        logPacient(p);
        return p;
    }

    public void logPacient(Pacient pacient) {
        PacientScoreLog log = new PacientScoreLog(pacient); // snapshot constructor
        pacientScoreLogRepository.save(log);
    }

    @Transactional
    public void deletePacientById(Long id) {
        // Optional: check if pacient exists
        Pacient pacient = pacientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pacient not found with id " + id));

        // Step 1: Delete their score logs
        pacientScoreLogRepository.deleteByPacientId(id);

        // Step 2: Delete the pacient
        pacientRepository.deleteById(id);
    }

    public Pacient updatePacient(Long id, PacientUpdateDTO updatedPacient) {
        return pacientRepository.findById(id).map(existing -> {
            // Identificare
            existing.UpdatePacient(updatedPacient);

            existing.setNume(updatedPacient.getNume());

            // COMA
            existing.setGcs(updatedPacient.getGcs());

            // Cardiac
            existing.setTensiune(updatedPacient.getTensiune());
            existing.setFrecventaCardiaca(updatedPacient.getFrecventaCardiaca());
            existing.setAreVasopresoare(updatedPacient.isAreVasopresoare());
            existing.setInstabilHemodinamic(updatedPacient.isInstabilHemodinamic());

            // Respirator
            existing.setTipRespiratie(updatedPacient.getTipRespiratie());
            existing.setSpo2(updatedPacient.getSpo2());
            existing.setOxigenStabil(updatedPacient.isOxigenStabil());

            // Infectios
            existing.setAreInfectie(updatedPacient.isAreInfectie());
            existing.setEsteContagios(updatedPacient.isEsteContagios());
            existing.setInfectieControlata(updatedPacient.isInfectieControlata());

            // Scoruri suplimentare
            existing.setSofa(updatedPacient.getSofa());
            existing.setApacheII(updatedPacient.getApacheII());
            existing.setLactat(updatedPacient.getLactat());

            existing.calculeazaToateScorurile();

            // Save updated pacient
            Pacient saved = pacientRepository.save(existing);

            // Log score snapshot
            logPacient(saved);

            return saved;
        }).orElseThrow(() -> new RuntimeException("Pacient not found with id " + id));
    }

    public Pacient makePacientWithUpdateDTO(PacientUpdateDTO newPacient){
        Pacient p = new Pacient();
        p.UpdatePacient(newPacient);
        return p;
    }

    public Pacient addPacient(Pacient p) {
        pacientRepository.save(p);
        logPacient(p);
        return p;
    }

/*    public boolean checkForUniqueBed(int bedNr){
        List<Pacient> pacients = pacientRepository.findAll();
        for (Pacient pacient : pacients) {
            if (pacient.getNrPat() == bedNr)
                return false;
        }
        return true;
    }*/
}
