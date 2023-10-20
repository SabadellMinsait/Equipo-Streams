package com.minsait.consultas.model.entity;

import jakarta.persistence.*;
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
    private int id;
    private Long idDoctor;
    private Date fecha;
    private String estatura;
    private String peso;
    private String temperatura;
    private String diagnostico;
    private String tratamiento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "historialmedico_id")
    private HistorialMedico historial;

}
