package com.minsait.doctores.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.websocket.server.PathParam;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;


@Entity
@Table(name = "doctores")
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @Pattern(regexp = "^[0-9]*$", message = "Solo se aceptan valores numericos")
    @Size(min = 6, max = 8)
    private String matricula;


    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;




}
