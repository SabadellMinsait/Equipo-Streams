package com.minsait.pacientes.controller;

import com.minsait.pacientes.model.entity.Direccion;
import com.minsait.pacientes.model.entity.Paciente;
import com.minsait.pacientes.repository.PacienteRepository;
import com.minsait.pacientes.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository service;

    @GetMapping("/obtienepacientes")
    @ResponseStatus(HttpStatus.OK)
    public List<Paciente> findAll (){
        return service.findAll();
    }

    @GetMapping("/obtienepacienteid/{id}")
    public ResponseEntity<Paciente> findById (@PathVariable Long id){
        Optional<Paciente> paciente = service.findById(id);
        if (!paciente.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                return ResponseEntity.ok(paciente.get());
    }

    @GetMapping("/obtienepacientenombre/{nombre}")
    public ResponseEntity<Paciente> findByNombre (@PathVariable String nombre){
        Optional<Paciente> paciente = service.findByNombre(nombre);
        if (!paciente.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(paciente.get());
    }

    @PostMapping("/insertpaciente")
    public ResponseEntity<Paciente> savePaciente(@RequestBody Paciente paciente){
        service.save(paciente);
        return new ResponseEntity<Paciente>(HttpStatus.CREATED);
    }
    @DeleteMapping("/deletepaciente")
    public ResponseEntity<Paciente> savePaciente(@PathVariable Long id){
        service.deleteById(id);
        return new ResponseEntity<Paciente>(HttpStatus.OK);
    }
}
