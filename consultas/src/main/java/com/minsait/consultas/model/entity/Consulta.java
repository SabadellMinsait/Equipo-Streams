package com.minsait.consultas.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "consultas")
@Getter
@Setter
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_consulta")
    @Pattern(regexp = "^[0-9]*$",message = "Solo se aceptan valores numericos")
    private int id;

    @NotNull
    @NotBlank(message = "El id de doctor no puede ir vacío")
    @Pattern(regexp = "^[0-9]*$",message = "Solo se aceptan valores numericos")
    private Long idDoctor;

    @NotNull
    @NotBlank(message = "La fecha no puede ir vacía")
    private Date fecha;

    private String estatura;
    private String peso;
    private String temperatura;

    @NotNull
    @NotBlank(message = "El diagnóstico puede ir vacío")
    private String diagnostico;

    @NotNull
    private String tratamiento;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "historialmedico_id")
    private HistorialMedico historial;

}
