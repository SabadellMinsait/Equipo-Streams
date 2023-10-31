package com.minsait.pacientes.repository;

import com.minsait.pacientes.model.entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    public void deleteById(Long id);

}
