package com.minsait.consultas.model.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class DoctorDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

}
