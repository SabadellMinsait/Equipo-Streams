package com.minsait.consultas.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacieminto;
    private int edad;
}
