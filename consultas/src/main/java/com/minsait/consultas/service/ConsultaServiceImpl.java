package com.minsait.consultas.service;

import com.minsait.consultas.model.entity.Consulta;
import com.minsait.consultas.model.entity.HistorialMedico;
import com.minsait.consultas.repository.ConsultaRepository;
import com.minsait.consultas.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements ConsultaService{
    @Autowired
    private ConsultaRepository consultarepository;

    @Autowired
    private HistorialRepository historialrepository;



    @Override
    public List<Consulta> findConsultas() {
        return consultarepository.findAll();
    }

    @Override
    public Optional<Consulta> findConsultaByID(Long id) {
         return consultarepository.findById(id);
    }

    @Override
    public void deleteConsultaById(Long id) {
        consultarepository.deleteById(id);
    }

    @Override
    public Consulta saveConsulta(Consulta consulta) {
        return consultarepository.save(consulta);
    }




    @Override
    public List<HistorialMedico> findHistorial() {
        return historialrepository.findAll();
    }

    @Override
    public Optional<HistorialMedico> findHistorialByID(Long id) {
        return historialrepository.findById(id);
    }

    @Override
    public void deleteHistorialById(Long id) {
        historialrepository.deleteById(id);
    }

    @Override
    public HistorialMedico saveHistorial(HistorialMedico historial) {
        return historialrepository.save(historial);
    }
}
