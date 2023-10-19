package com.minsait.doctores.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "especialidad")
@Getter
@Setter
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Long id;

    @NotNull
    private String nombre;

    @ManyToOne()
    @JsonIgnore
    private Doctor doctor;


}
