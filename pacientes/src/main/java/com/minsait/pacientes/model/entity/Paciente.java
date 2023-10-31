package com.minsait.pacientes.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.DirectoryIteratorException;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="paciente")
@Getter
@Setter
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    @NotBlank(message = "El campo Nombre no puede ir vacío")
    private String nombre;
    @NotBlank(message = "El campo Apellido Paterno no puede ir vacío")
    private String apellidoPaterno;
    @NotBlank(message = "El campo Apellido Materno no puede ir vacío")
    private String apellidoMaterno;
    //@NotBlank(message = "El campo Fecha de Nacimiento no puede ir vacío")
    private Date fechaNacieminto;
    private int edad;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="direccion_id", referencedColumnName = "id")
    private Direccion direccion;
}
