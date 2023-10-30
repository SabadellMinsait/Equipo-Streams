package com.minsait.pacientes.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Data
public class HistorialDTO {

    public Long id;

    public Long idPaciente;

    public List<Consulta> consulta;

}
