package com.minsait.consultas.repository;

import com.minsait.consultas.model.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
}
