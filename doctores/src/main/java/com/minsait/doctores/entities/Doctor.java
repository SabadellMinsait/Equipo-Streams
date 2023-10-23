package com.minsait.doctores.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctor")
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
    private Long id_especialidad;

    @OneToMany(mappedBy = "doctor")
    @JoinColumn(name = "especialidad_id")
    private List<Especialidad>especialidad;




}
