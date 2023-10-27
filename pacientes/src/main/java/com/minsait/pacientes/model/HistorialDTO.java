package com.minsait.pacientes.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Data
public class HistorialDTO {

    public Long idhistorial;

    public Long idPaciente;

    public List<Consulta> consulta;

}
