package com.prioritizareAti.prioritizareAti.Pacient;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacients")
public class PacientController {

    @Autowired
    private PacientRepository pacientRepository;

    @Operation(summary = "Generare de pacient aleator", description = "Genereaza un pacient inexistent")
    @GetMapping("/generate")
    public Pacient generarePacient() {
        return pacientRepository.save(GeneratorPacient.genereazaPacientRandom());
    }

    @PostMapping("/create")
    public Pacient create(@RequestBody Pacient pacient) {
        return pacientRepository.save(pacient);
    }

}
