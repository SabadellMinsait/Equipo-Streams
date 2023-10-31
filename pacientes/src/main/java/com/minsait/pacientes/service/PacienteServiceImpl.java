package com.minsait.pacientes.service;

import com.minsait.pacientes.clients.ConsultaClient;
import com.minsait.pacientes.model.HistorialDTO;
import com.minsait.pacientes.model.PacienteDTO;
import com.minsait.pacientes.model.entity.Paciente;
import com.minsait.pacientes.repository.DireccionRepository;
import com.minsait.pacientes.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class PacienteServiceImpl implements PacienteService, DireccionService  {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    DireccionRepository direccionRepository;

    @Autowired
    ConsultaClient historialClient;

    @Override
    public List<Paciente> findAll(){
        return repository.findAll();
    }

    @Override
    public Optional<Paciente> findById(Long id){
        return repository.findById(id);
    }

    @Override
    public Optional<PacienteDTO> findbyIdDTO(Long id){
        Optional<Paciente> pacienteEncontrado = repository.findById(id);
        PacienteDTO pacienteDTO = new PacienteDTO();
        if (!pacienteEncontrado.isPresent())
            return Optional.empty();
        try {
            pacienteDTO.setId(pacienteEncontrado.get().getId());
            pacienteDTO.setNombre(pacienteEncontrado.get().getNombre());
            pacienteDTO.setApellidoPaterno(pacienteEncontrado.get().getApellidoPaterno());
            pacienteDTO.setApellidoMaterno(pacienteEncontrado.get().getApellidoMaterno());
            pacienteDTO.setEdad(pacienteEncontrado.get().getEdad());
            pacienteDTO.setFechaNacieminto(pacienteEncontrado.get().getFechaNacieminto());
            pacienteDTO.setDireccion(pacienteEncontrado.get().getDireccion());
            pacienteDTO.setTotalConsuta((long) 0);
            PacienteDTO pacienteConsulta = new PacienteDTO();
            Optional<PacienteDTO> pacienteObtConsulta = findConsulta(id);
            if (pacienteObtConsulta.isPresent())
                pacienteDTO.setHistorialDTO(pacienteObtConsulta.get().getHistorialDTO());

            try {
                pacienteDTO.setTotalConsuta(pacienteObtConsulta.get().getHistorialDTO().getConsulta().stream().count());
            }catch (Exception e) {
                pacienteDTO.setTotalConsuta((long) 0);
            }

            return Optional.of(pacienteDTO);

        }catch(Exception e) {
            throw new IllegalArgumentException("Error " + e.getMessage());
        }
    }

    @Override
    public Optional<Paciente> findByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    @Override
    @Transactional
    public Paciente save(@NotNull Paciente paciente){
        Paciente pacienteDatos = new Paciente();
        String nombre = pacienteDatos.getNombre();
        try {
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
        historialClient.deleteHistorialById(id);
        repository.deleteById(id);
        direccionRepository.deleteById(id);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No souch Consulta")
    public Optional<PacienteDTO> findConsulta(Long id) {
        PacienteDTO pacienteDTOhistorial = new PacienteDTO();
        try {
            pacienteDTOhistorial.setHistorialDTO(historialClient.findHistorialMedicoByPacienteID(id).getBody());
            return Optional.of(pacienteDTOhistorial);
        }catch (Exception e){
            return Optional.of(pacienteDTOhistorial);
        }
    }

}
