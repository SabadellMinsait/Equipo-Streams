package com.minsait.doctores.entities;

import com.sun.istack.internal.NotNull;
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


    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String matricula;
    private Long id_especialidad;

    @OneToMany(mappedBy = "doctor")
    @JoinColumn(name = "especialidad_id")
    private List<Especialidad>especialidad;




}
