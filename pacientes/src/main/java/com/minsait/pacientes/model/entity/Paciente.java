package com.minsait.pacientes.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
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
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacieminto;
    private int edad;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="direccion_id", referencedColumnName = "id")
    private Direccion direccion;
}
