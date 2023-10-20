package com.minsait.pacientes.service;

import com.minsait.pacientes.repository.DireccionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public class DireccionServiceImpl implements DireccionService {

    DireccionRepository repository;

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
