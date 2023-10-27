package com.minsait.pacientes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Consulta {

    public int id;
    public Long idDoctor;
    public Date fecha;
    public String estatura;
    public String peso;
    public String temperatura;
    public String diagnostico;
    public String tratamiento;
    @JsonIgnore
    public List<HistorialDTO> historialDTOList;
}
