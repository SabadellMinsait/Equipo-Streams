package com.minsait.doctores.service;


import com.minsait.doctores.entities.Especialidad;
import com.minsait.doctores.repository.EspecialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EspecialidadServImpl implements EspecialidadService {
    @Autowired
    EspecialRepository especialidadrepository;

    @Override
    @Transactional
    public List<Especialidad> listarEspecialidad() {

        return (List<Especialidad>) especialidadrepository.findAll();
    }
    @Override
    @Transactional
    public Optional<Especialidad> obtenerEspecialidad(Long id) {

        return especialidadrepository.findById(id);
    }
    @Override
    @Transactional
    public Especialidad guardarEspecialidad(Especialidad especialidad) {

        return especialidadrepository.save(especialidad);
    }

    @Override
    @Transactional
    public void eliminarEspecialidad(Long id) {

        especialidadrepository.deleteById(id);
    }



}
