package com.minsait.consultas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "historiales")
@Setter
@Getter
public class HistorialMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_historial")
    private Long id;

    private int idPaciente;
    private String descripcion;


    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "historial")

    private List<Consulta> consulta;

    public HistorialMedico(){
        this.consulta=new ArrayList<>();
    }
}
