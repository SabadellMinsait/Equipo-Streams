package com.minsait.doctores.controller;

import com.minsait.doctores.entities.Doctor;
import com.minsait.doctores.entities.Especialidad;
import com.minsait.doctores.service.DoctorService;
import com.minsait.doctores.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
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
    public ResponseEntity<?> obtenerDoctor(@PathVariable Long id){
        Optional <Doctor> doctor = doctorservice.obtenerDoctor(id);
        if(doctor.isPresent()){

            return ResponseEntity.ok(doctor.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDoctor(@PathVariable Long id, @RequestBody Doctor doctor){
        Optional <Doctor> doctorA = doctorservice.obtenerDoctor(id);
        if (doctorA.isPresent()){

            Doctor doctoractualizar = doctorA.get();
            doctoractualizar.setNombre(doctor.getNombre());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> guardarDoctor(@RequestBody Doctor doctor){

        Doctor doctorGuardado = doctorservice.guardarDoctor(doctor);
        return ResponseEntity.ok(doctorGuardado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDoctor(@PathVariable Long id){

        Optional <Doctor> doctoreliminado = doctorservice.obtenerDoctor(id);
        if(doctoreliminado.isPresent()){

            doctorservice.eliminarDoctor(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/Registrar_Especialidad/{especialidad_id}")
    public ResponseEntity<?>registrarEspecialDoc(@RequestBody Doctor doctor, @PathVariable Long especialidad_id){
        Doctor doctor1 = doctorservice.regEspDoc(doctor, especialidad_id);

        return  ResponseEntity.status(HttpStatus.CREATED).body(doctor1);
    }

    @PostMapping("/Asignar_Especialidad/{id_doctor}/{especialidad_id}")
    public ResponseEntity<?>asignarEspecialDoc(@PathVariable Long id_doctor, Long especialidad_id){

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

}
