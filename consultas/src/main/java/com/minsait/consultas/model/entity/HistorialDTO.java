package com.minsait.consultas.model.entity;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HistorialDTO {
    private Long id;
    private Long idPaciente;
    private List<Consulta> consulta;
}
