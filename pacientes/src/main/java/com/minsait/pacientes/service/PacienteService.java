package com.minsait.pacientes.service;

import com.minsait.pacientes.model.entity.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    public List<Paciente> findAll();
    public Optional<Paciente> findById(Long id);

    public Optional<Paciente> findByNombre(String nombre);

    public Optional<Paciente> savePaciente(Paciente paciente);

    public Optional<Paciente> deleteById(Long id);

}
