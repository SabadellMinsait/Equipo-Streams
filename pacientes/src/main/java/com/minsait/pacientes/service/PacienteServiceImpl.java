package com.minsait.pacientes.service;

import com.minsait.pacientes.model.entity.Paciente;
import com.minsait.pacientes.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;

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
    public Optional<Paciente> savePaciente(@RequestBody Paciente paciente){
        Paciente pacienteDatos = new Paciente();
        //Optional<Paciente> validar = Optional.of(new Paciente());
        /*if (!validar.stream().map().)
            return new ResponseEntity<Paciente>(HttpStatus.CONFLICT);*/

        pacienteDatos.setDireccion(paciente.getDireccion());
        pacienteDatos.setId(pacienteDatos.getId());
        pacienteDatos.setNombre(paciente.getNombre());
        pacienteDatos.setApellidoPaterno(paciente.getApellidoPaterno());
        pacienteDatos.setApellidoMaterno(paciente.getApellidoMaterno());
        pacienteDatos.setFechaNacieminto(paciente.getFechaNacieminto());
        pacienteDatos.setEdad(paciente.getEdad());
        repository.save(paciente);
        return repository.findByNombre(pacienteDatos.getNombre());

    }

    @Override
    public Optional<Paciente> deleteById (@PathVariable Long id){
        repository.deleteById(id);
        return null;
    }

}
