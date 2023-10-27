package com.minsait.doctores.controller;

import com.minsait.doctores.entities.Doctor;
import com.minsait.doctores.entities.Especialidad;
import com.minsait.doctores.service.DoctorService;
import com.minsait.doctores.service.EspecialidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/v1/api/especialidades")
public class EspecialidadController {

    @Autowired
    EspecialidadService especialidadservice;

    @Autowired
    DoctorService doctorservice;
    @GetMapping
    public List<Especialidad> listaDeEspecialidad(){
        return especialidadservice.listarEspecialidad();

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEspecialidad(@PathVariable Long id){
        Optional<Especialidad> especialidad = especialidadservice.obtenerEspecialidad(id);
        if(especialidad.isPresent()){

            return ResponseEntity.ok(especialidad.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEspecialidad(@PathVariable Long id, @RequestBody @Valid Especialidad especialidad, BindingResult result){
      /*  if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error de validación " + result.getAllErrors());
        }*/
        if (result.hasErrors()){
            return validar(result);
        }
        Optional <Especialidad> especialA = especialidadservice.obtenerEspecialidad(id);
        if (especialA.isPresent()){

            Especialidad especialidadActualizar = especialA.get();
            especialidadActualizar.setNombre(especialidad.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(especialidadActualizar);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> saveEspecialidad(@RequestBody @Valid Especialidad especialidad, BindingResult result){
       /* if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error de validación " + result.getAllErrors());
        }*/
        if (result.hasErrors()){
            return validar(result);
        }
        Especialidad especialidadGuardado = especialidadservice.guardarEspecialidad(especialidad);
        return ResponseEntity.ok(especialidadGuardado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEspecialidad(@PathVariable Long id){

        Optional <Especialidad> especialidadeliminada = especialidadservice.obtenerEspecialidad(id);
        if(especialidadeliminada.isPresent()){

            especialidadservice.eliminarEspecialidad(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{especialidad_id}/Doctores-Especialidad")
    public ResponseEntity<?>findDoctorEspecialidad(@PathVariable Long especialidad_id){

        Optional <Especialidad>especialidadOp = especialidadservice.obtenerEspecialidad(especialidad_id);
        if (especialidadOp.isPresent()){
            Especialidad especialidad = especialidadOp.get();
           List<Doctor>doctores= doctorservice.obtenerDoctoresEspecial(especialidad);
            return ResponseEntity.ok().body(doctores);
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}

