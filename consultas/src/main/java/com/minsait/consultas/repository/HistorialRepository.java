package com.minsait.consultas.repository;

import com.minsait.consultas.model.entity.Consulta;
import com.minsait.consultas.model.entity.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialRepository extends JpaRepository<HistorialMedico,Long> {
}
