package com.minsait.consultas.service;

import com.minsait.consultas.model.entity.Consulta;
import com.minsait.consultas.model.entity.HistorialMedico;

import java.util.List;
import java.util.Optional;

public interface ConsultaService {

    public List<Consulta>findConsultas();
    public Optional<Consulta>findConsultaByID(Long id);
    public void deleteConsultaById(Long id);
    public Consulta saveConsulta(Consulta consulta);


    public Optional<HistorialMedico>findByidPaciente(Long id);




    public List<HistorialMedico>findHistorial();
    public Optional<HistorialMedico>findHistorialByID(Long id);
    public void deleteHistorialById(Long id);
    public HistorialMedico saveHistorial(HistorialMedico historial);
}
