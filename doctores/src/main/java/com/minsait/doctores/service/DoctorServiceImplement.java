package com.minsait.doctores.service;

import com.minsait.doctores.repository.DoctoresRepository;
import com.minsait.doctores.entities.Doctor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DoctorServiceImplement implements DoctorService{
    @Autowired
    DoctoresRepository doctoresrepository;
    @Override
    public List<Doctor> listarDoctores() {
        return (List<Doctor>) doctoresrepository.findAll();
    }

    @Override
    public Optional<Doctor> obtenerDoctor(Long id_doctor) {
        return doctoresrepository.findById(id_doctor);
    }

    @Override
    public Doctor guardarDoctor(Doctor doctor) {
        return doctoresrepository.save(doctor);
    }

    @Override
    public void eliminarDoctor(Long id_doctor) {
        doctoresrepository.deleteById(id_doctor);
    }
}
