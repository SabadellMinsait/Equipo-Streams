package com.minsait.consultas.service;

import com.minsait.consultas.model.entity.Consulta;
import com.minsait.consultas.model.entity.DoctorDTO;
import com.minsait.consultas.model.entity.HistorialMedico;
import com.minsait.consultas.repository.ConsultaRepository;
import com.minsait.consultas.repository.HistorialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements ConsultaService{
    @Autowired
    private ConsultaRepository consultarepository;

    @Autowired
    private HistorialRepository historialrepository;



    @Override
    @Transactional
    public List<Consulta> findConsultas() {
        return consultarepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Consulta> findConsultaByID(Long id) {
         return consultarepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteConsultaById(Long id) {
        consultarepository.deleteById(id);
    }

    @Override
    @Transactional
    public Consulta saveConsulta(Consulta consulta) {

        return consultarepository.save(consulta);
    }




    @Override
    @Transactional
    public List<HistorialMedico> findHistorial() {
        return historialrepository.findAll();
    }

    @Override
    @Transactional
    public Optional<HistorialMedico> findHistorialByID(Long id) {
        return historialrepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteHistorialById(Long id) {
        historialrepository.deleteById(id);
    }

    @Override
    @Transactional
    public HistorialMedico saveHistorial(HistorialMedico historial) {
        return historialrepository.save(historial);
    }


    @Override
    @Transactional
    public Optional<HistorialMedico> findByidPaciente(Long id) {
        return historialrepository.findByidPaciente(id);
    }

}
