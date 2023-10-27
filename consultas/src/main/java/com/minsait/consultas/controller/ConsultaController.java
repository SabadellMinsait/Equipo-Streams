package com.minsait.consultas.controller;

import com.minsait.consultas.clients.DoctoresClient;
import com.minsait.consultas.clients.PacientesClient;
import com.minsait.consultas.model.entity.*;
import com.minsait.consultas.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<?>historialId =doctoresService.obtenerDoctor(consulta.getHistorial().getId());
        Optional<?> doctorId =findByidDoctor(consulta.getIdDoctor());
        if (!doctorId.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!historialId.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.saveConsulta(consulta));
        }catch (Error e){
            throw new IllegalArgumentException(e.getMessage());
        }

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
        service.deleteHistorialById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/historial/{id}")
    public ResponseEntity<HistorialMedico>updateHistorialMedico(@PathVariable Long id,@RequestBody HistorialMedico historial){
        Optional<HistorialMedico>historial1=service.findHistorialByID(id);
        Optional<PacienteDTO>pacienteId =pacientesService.findById(historial.getIdPaciente());
        if (!historial1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!pacienteId.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HistorialMedico HistorialMedicoAct=historial1.get();
        HistorialMedicoAct.setIdPaciente(historial.getIdPaciente());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveHistorial(HistorialMedicoAct));
    }

    @PostMapping("/historial")
    public ResponseEntity<HistorialMedico>saveHistorialMedico(@RequestBody HistorialMedico historial){
       Optional<?>pacienteId =pacientesService.findById(historial.getIdPaciente());
        if (!pacienteId.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveHistorial(historial));
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No souch of data")
    public Optional<Consulta> findByidDoctor(Long id) {
        Consulta consultaNew=new Consulta();
        try {
            consultaNew.setIdDoctor(doctoresService.obtenerDoctor(id).get().getId());
            return Optional.of(consultaNew);
        }catch (Exception e){
            return Optional.of(consultaNew);
        }
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No souch of data")
    public Optional<HistorialMedico> findByidPaciente(Long id) {
        HistorialMedico historialNew=new HistorialMedico();
        try {
            historialNew.setIdPaciente(pacientesService.findById(id).get().getId());
            return Optional.of(historialNew);
        }catch (Exception e){
            return Optional.of(historialNew);
        }
    }


}
