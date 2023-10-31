package com.minsait.pacientes.model;

import com.minsait.pacientes.model.entity.Direccion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PacienteDTO {

      Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacieminto;
    private int edad;
    private Direccion direccion;
    private HistorialDTO historialDTO;
    public Long totalConsuta;
}
