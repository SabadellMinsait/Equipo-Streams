package com.minsait.doctores.service;

import com.minsait.doctores.entities.Doctor;
import com.minsait.doctores.entities.Especialidad;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {

    List<Especialidad> listarEspecialidad();

    Optional<Especialidad> obtenerEspecialidad(Long id);

    Especialidad guardarEspecialidad(Especialidad especialidad);

    void eliminarEspecialidad(Long id);


}
