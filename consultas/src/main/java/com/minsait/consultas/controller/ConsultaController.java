package com.minsait.consultas.controller;

import com.minsait.consultas.clients.DoctoresClient;
import com.minsait.consultas.clients.PacientesClient;
import com.minsait.consultas.model.entity.*;
import com.minsait.consultas.service.ConsultaService;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api")
public class
ConsultaController {
    @Autowired
    private ConsultaService service;
    @Autowired
    private PacientesClient pacientesService;
    @Autowired
    private DoctoresClient doctoresService;

    @GetMapping("/consultas")
    public List<Consulta> findAll(){
        return service.findConsultas();
    }


    @GetMapping("/consultas/{id}")
    public ResponseEntity<Consulta> findConsultaById(@PathVariable Long id){
        Optional<Consulta> consulta= service.findConsultaByID(id);
        if (!consulta.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return ResponseEntity.ok(consulta.get());

    }


    @DeleteMapping("/consultas/{id}")
    public ResponseEntity<Consulta>deleteById(@PathVariable Long id){
        Optional<Consulta>consulta=service.findConsultaByID(id);
        if (!consulta.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteConsultaById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/consultas")
    public ResponseEntity<Consulta>saveConsulta(@RequestBody Consulta consulta){
        Optional<?>doctorID =doctoresService.obtenerDoctor(consulta.getIdDoctor());
        Optional<?> historialID =service.findHistorialByID(consulta.getHistorial().getId());
        if (!doctorID.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!historialID.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (historialID.get() == consulta.getHistorial().getId())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveConsulta(consulta));

    }








    @GetMapping("/historial")
    public List<HistorialMedico> findHistorialAll(){
        return service.findHistorial();
    }


    @GetMapping("/historial/{id}")
    public ResponseEntity<HistorialMedico> findHistorialMedicoById(@PathVariable Long id){
        Optional<HistorialMedico> historial= service.findHistorialByID(id);
        if (!historial.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(historial.get());
    }


    @GetMapping("/historial-paciente/{id}")
    public ResponseEntity<HistorialDTO> findHistorialMedicoByPacienteID(@PathVariable Long id){
        Optional<HistorialMedico> historial= service.findByidPaciente(id);
        if (!historial.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HistorialMedico HistorialMedicoAct=historial.get();
        HistorialDTO historialDTO = new HistorialDTO();

        historialDTO.setIdPaciente(HistorialMedicoAct.getIdPaciente());
        historialDTO.setId(HistorialMedicoAct.getId());
        historialDTO.setConsulta(HistorialMedicoAct.getConsulta());

        return ResponseEntity.ok(historialDTO);
    }

    @DeleteMapping("/historial/{id}")
    public ResponseEntity<HistorialMedico>deleteHistorialById(@PathVariable Long id){
        Optional<HistorialMedico>historial=service.findHistorialByID(id);

        if (!historial.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Consulta>consultasEliminar=historial.get().getConsulta().stream().collect(Collectors.toList());

        for (int i=0;i<consultasEliminar.stream().count();i++) {
            service.deleteConsultaById((long)consultasEliminar.get(i).getId());
        }
        service.deleteHistorialById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/historial")
    public ResponseEntity<HistorialMedico>saveHistorialMedico(@RequestBody HistorialMedico historial){
       Optional<?>pacienteId =pacientesService.findById(historial.getIdPaciente());
       Optional<HistorialMedico> historialPaciente= service.findByidPaciente(historial.getIdPaciente());

        if (historialPaciente.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!pacienteId.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveHistorial(historial));
    }


}
