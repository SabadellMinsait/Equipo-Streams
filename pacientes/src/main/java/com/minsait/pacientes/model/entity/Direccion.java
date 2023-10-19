package com.minsait.pacientes.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="paciente_direccion")
@Getter
@Setter
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    Long id;
    private String calle;
    private int numero;
    private int codigoPostal;
    private String colonia;
    private String estado;
    @OneToOne(mappedBy = "direccion")
    @JsonIgnore
    private Paciente paciente;
}
