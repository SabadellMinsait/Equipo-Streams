package com.minsait.doctores.service;

import com.minsait.doctores.entities.Especialidad;
import com.minsait.doctores.repository.DoctoresRepository;
import com.minsait.doctores.entities.Doctor;
import com.minsait.doctores.repository.EspecialRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImplement implements DoctorService{
    @Autowired
    DoctoresRepository doctoresrepository;
    @Autowired
    EspecialRepository especialidadrepository;

    @Override
    @Transactional
    public List<Doctor> listarDoctores() {

        return (List<Doctor>) doctoresrepository.findAll();
    }
    @Override
    @Transactional
    public Optional<Doctor> obtenerDoctor(Long id_doctor) {

        return doctoresrepository.findById(id_doctor);
    }
    @Override
    @Transactional
    public Doctor guardarDoctor(Doctor doctor) {

        return doctoresrepository.save(doctor);
    }

    @Override
    @Transactional
    public void eliminarDoctor(Long id_doctor) {

        doctoresrepository.deleteById(id_doctor);
    }

    @Override
    @Transactional
    public Doctor regEspDoc(Doctor doctor, Long especialidad_id){

        Optional<Especialidad>especialidadOp = especialidadrepository.findById(especialidad_id);
        if (especialidadOp.isPresent()){
            Especialidad especialidad = especialidadOp.get();
            doctor.setEspecialidad(especialidad);
            return doctoresrepository.save(doctor);

        }
        throw new EntityNotFoundException("El id no fue encontrado");
    }

    @Override
    public List<Doctor> obtenerDoctoresEspecial(Especialidad especialidad) {
            return doctoresrepository.findByEspecialidad(especialidad);
    }
}
