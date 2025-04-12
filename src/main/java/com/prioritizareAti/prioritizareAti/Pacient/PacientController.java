package com.prioritizareAti.prioritizareAti.Pacient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacients")
public class PacientController {

    final PacientService pacientService;

    @Autowired
    public PacientController(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    @GetMapping("/getPacients")
    public List<PacientSimpleDTO> getPacients() {
        return pacientService.getSimpleDTOList();
    }

    @GetMapping("/getPacientDetails/{id}")
    public PacientDetailsDTO getPacientDetails(@PathVariable Long id){
        return pacientService.getPacientDetails(id);
    }

    @PostMapping("/generate")
    public Pacient generarePacient() {
        return pacientService.generatePacient();
    }

    @DeleteMapping("/deletePacient/{id}")
    public void deletePacient(@PathVariable Long id){
        pacientService.deletePacientById(id);
    }

    @PutMapping("/update/{id}")
    public Pacient updatePacient(@PathVariable Long id, @RequestBody PacientUpdateDTO updatedPacient){
        return pacientService.updatePacient(id, updatedPacient);
    }
    @PostMapping("/addWithUpdateDTO")
    public Pacient addPacientWithUpdateDTO(@RequestBody PacientUpdateDTO newPacientUpdateDTO){
        return pacientService.addPacient(pacientService.makePacientWithUpdateDTO(newPacientUpdateDTO));
    }

    @GetMapping("/tryFinalScore")
    public int tempFinalScore(@RequestParam PacientUpdateDTO tempPacient){
        Pacient p = pacientService.makePacientWithUpdateDTO(tempPacient);
        p.calculeazaToateScorurile();
        return p.getScorGeneralFinal();
    }

}
