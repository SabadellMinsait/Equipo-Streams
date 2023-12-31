package com.minsait.pacientes.repository;

import com.minsait.pacientes.model.HistorialDTO;
import com.minsait.pacientes.model.PacienteDTO;
import com.minsait.pacientes.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository  extends JpaRepository<Paciente, Long>{

    Optional<Paciente> findByNombre(String nombre);


    public void deleteById(Long id);


    //Optional<Paciente> findbyIdDTO(Long id);
}
