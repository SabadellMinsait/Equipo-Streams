package com.minsait.pacientes.service;

import com.minsait.pacientes.model.entity.Paciente;
import com.minsait.pacientes.repository.DireccionRepository;
import com.minsait.pacientes.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService, DireccionService  {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    DireccionRepository direccionRepository;

    @Override
    public List<Paciente> findAll(){
        return repository.findAll();
    }

    @Override
    public Optional<Paciente> findById(Long id){
        return repository.findById(id);
    }

    @Override
    public Optional<Paciente> findByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    @Override
    @Transactional
    public Paciente save(@NotNull Paciente paciente){
        Paciente pacienteDatos = new Paciente();
        try {
        if (paciente.getNombre().length()==0)
            throw new IllegalArgumentException("El nombre no puede ir vacio");
        pacienteDatos.setDireccion(paciente.getDireccion());
        pacienteDatos.setNombre(paciente.getNombre());
        pacienteDatos.setApellidoPaterno(paciente.getApellidoPaterno());
        pacienteDatos.setApellidoMaterno(paciente.getApellidoMaterno());
        pacienteDatos.setFechaNacieminto(paciente.getFechaNacieminto());
        pacienteDatos.setEdad(paciente.getEdad());

            return repository.save(paciente);
        }catch (Exception e){
            if (e.getMessage().contains("null"))
                throw new IllegalArgumentException("No pueden haber campos con valor null");
            throw new IllegalArgumentException("Error al registar el paciente");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        repository.deleteById(id);
        direccionRepository.deleteById(id);
    }
}
