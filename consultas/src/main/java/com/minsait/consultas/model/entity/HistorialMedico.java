package com.minsait.consultas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[0-9]*$",message = "Solo se aceptan valores numericos")
    private Long id;

    @NotNull
    @NotBlank(message = "El id de paciente no puede ir vacío")
    @Pattern(regexp = "^[0-9]*$",message = "Solo se aceptan valores numericos")
    private Long idPaciente;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "historial")
    @JsonIgnore
    private List<Consulta> consulta;

    public HistorialMedico(){
        this.consulta=new ArrayList<>();
    }
}
