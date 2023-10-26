package com.minsait.doctores.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "especialidades")
@Getter
@Setter
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
   private Long id;
    private String nombre;

    @OneToMany(mappedBy = "especialidad", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Doctor> doctores;


}
