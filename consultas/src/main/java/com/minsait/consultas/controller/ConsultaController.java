package com.minsait.consultas.controller;

import com.minsait.consultas.model.entity.Consulta;
import com.minsait.consultas.model.entity.HistorialMedico;
import com.minsait.consultas.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api")
public class ConsultaController {
    @Autowired
    ConsultaService service;

    @GetMapping("/consultas")
    public List<Consulta> findAll(){
        return service.findConsultas();
    }


    @GetMapping("/consultas/{id}")
    public ResponseEntity<Consulta> findConsultaById(@PathVariable Long id){
        Optional<Consulta> consulta= service.findConsultaByID(id);
        if (!consulta.isPresent()){
            return ResponseEntity.notFound().build();
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


    @PutMapping("/consultas/{id}")
    public ResponseEntity<Consulta>updateConsulta(@PathVariable Long id,@RequestBody Consulta consulta){
        Optional<Consulta>consulta1=service.findConsultaByID(id);
        if (!consulta1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Consulta consultaAct=consulta1.get();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveConsulta(consultaAct));
    }


    @PostMapping("/consultas")
    public ResponseEntity<Consulta>saveConsulta(@RequestBody Consulta consulta){
        Optional<?>historialId =service.findHistorialByID(consulta.getHistorial().getId());
        //Optional<?>doctorId =service.findDoctorByID(consulta.getIdDoctor);
        //!historialId.isPresent() or  !doctorId.isPresent()
        if (!historialId.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historial.get());
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
        if (!historial1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HistorialMedico HistorialMedicoAct=historial1.get();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveHistorial(HistorialMedicoAct));
    }


    @PostMapping("/historial")
    public ResponseEntity<HistorialMedico>saveHistorialMedico(@RequestBody HistorialMedico historial){
       //Optional<?>pacienteId =service.findPersonaByID(histoorial.getIdPaciente);
       // if (!pacienteId.isPresent()){
        //    return ResponseEntity.notFound().build();
       // }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveHistorial(historial));
    }



    public ResponseEntity<?>saveConsul(Consulta consulta){
        if (consulta.getHistorial()!=null){
            Optional<HistorialMedico>historial=service.findHistorialByID(consulta.getHistorial().getId());
            historial.ifPresent(consulta::setHistorial);
        }
        //if (consulta.getHistorial()!=null){
       //     Optional<HistorialMedico>historial=service.findHistorialByID(consulta.getHistorial().getId());
         //   historial.ifPresent(consulta::setHistorial);
       // }
        try{
            return ResponseEntity.ok(service.saveConsulta(consulta));
        }catch (Exception e){
                throw new IllegalArgumentException("Error al registrar la consulta");
        }


    }


}
