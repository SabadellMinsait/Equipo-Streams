package com.minsait.doctores.service;

import com.minsait.doctores.entities.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor>listarDoctores();

    Optional<Doctor>obtenerDoctor(Long id_doctor);

    Doctor guardarDoctor(Doctor doctor);

    void eliminarDoctor(Long id_doctor);

}
