package com.minsait.doctores.repository;

import com.minsait.doctores.entities.Doctor;
import com.minsait.doctores.entities.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctoresRepository extends CrudRepository<Doctor, Long> {
    List<Doctor> findByEspecialidad(Especialidad especialidad);
}
