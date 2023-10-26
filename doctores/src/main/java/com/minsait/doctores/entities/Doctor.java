package com.minsait.doctores.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctores")
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;


    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;




}
