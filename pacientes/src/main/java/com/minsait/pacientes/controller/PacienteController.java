package com.minsait.pacientes.controller;

import com.minsait.pacientes.model.entity.Direccion;
import com.minsait.pacientes.model.entity.Paciente;
import com.minsait.pacientes.repository.PacienteRepository;
import com.minsait.pacientes.service.PacienteService;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> savePaciente(@RequestBody Paciente paciente){
        ResponseEntity<?> responseEntity= save(paciente);
        if(responseEntity.getStatusCode().equals(HttpStatus.OK))
            return ResponseEntity.status(HttpStatus.CREATED).body(responseEntity.getBody());
        return responseEntity;
    }

    @DeleteMapping("/deletepaciente/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable Long id){
        Optional<Paciente> validaDatos= service.findById(id);
        if (!validaDatos.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updatepaciente")
    public ResponseEntity<?> updatePaciente(@RequestBody Paciente paciente){
        Optional<Paciente> validaDatos = service.findById(paciente.getId());
        if (!validaDatos.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<?> responseEntity= save(paciente);
        if(responseEntity.getStatusCode().equals(HttpStatus.OK))
            return ResponseEntity.status(HttpStatus.CREATED).body(responseEntity.getBody());
        return responseEntity;
    }

    public ResponseEntity<?> save(@NotNull Paciente paciente){
        try {
            return ResponseEntity.ok(service.save(paciente));
        }catch (Exception e){
            Map<String, Object> error= new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
