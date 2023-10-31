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
@RequestMapping("/v1/api/doctores")
public class DoctorController {
    @Autowired
    DoctorService doctorservice;
    @Autowired
    EspecialidadService especialidadservice;
    @GetMapping
    public List<Doctor> listaDeDoctores(){
        return doctorservice.listarDoctores();

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findDoctor(@PathVariable Long id){
        Optional <Doctor> doctor = doctorservice.obtenerDoctor(id);
        if(doctor.isPresent()){

            return ResponseEntity.ok(doctor.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody @Valid Doctor doctor, BindingResult result){
       /* if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error de validación " + result.getAllErrors());
        }*/
        if (result.hasErrors()){
            return validar(result);
        }
        Optional <Doctor> doctorA = doctorservice.obtenerDoctor(id);
        if (doctorA.isPresent()){

            Doctor doctoractualizar = doctorA.get();
            doctoractualizar.setNombre(doctor.getNombre());
            doctoractualizar.setApellido(doctor.getApellido());
            doctoractualizar.setMatricula(doctor.getMatricula());
            return ResponseEntity.status(HttpStatus.CREATED).body(doctoractualizar);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> saveDoctor(@RequestBody @Valid Doctor doctor, BindingResult result){
    /*if (result.hasErrors()){
    return ResponseEntity.badRequest().body("Error de validación " + result.getAllErrors());
}*/
        if (result.hasErrors()){
            return validar(result);
        }
        Doctor doctorGuardado = doctorservice.guardarDoctor(doctor);
        return ResponseEntity.ok(doctorGuardado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id){

        Optional <Doctor> doctoreliminado = doctorservice.obtenerDoctor(id);
        if(doctoreliminado.isPresent()){

            doctorservice.eliminarDoctor(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/Registrar_Especialidad/{especialidad_id}")
    public ResponseEntity<?>regEspecialDoc(@RequestBody @Valid Doctor doctor, @PathVariable Long especialidad_id, BindingResult result){
       /* if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Error de validación " + result.getAllErrors());
        }*/
        if (result.hasErrors()){
            return validar(result);
        }

        Doctor doctor1 = doctorservice.regEspDoc(doctor, especialidad_id);

        return  ResponseEntity.status(HttpStatus.CREATED).body(doctor1);
    }

    @PostMapping("/Asignar_Especialidad/{id_doctor}/{especialidad_id}")
    public ResponseEntity<?>asignEspecialDoc(@PathVariable Long id_doctor, Long especialidad_id){

        Optional<Doctor> doctorOp = doctorservice.obtenerDoctor(id_doctor);
        Optional<Especialidad> especialidadOpt = especialidadservice.obtenerEspecialidad(especialidad_id);
        if (doctorOp.isPresent() && especialidadOpt.isPresent()){
            Doctor doctor = doctorOp.get();
            Especialidad especialidad =especialidadOpt.get();
            doctor.setEspecialidad(especialidad);
            doctorservice.guardarDoctor(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
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
