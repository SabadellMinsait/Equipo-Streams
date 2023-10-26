package com.minsait.doctores.repository;

import com.minsait.doctores.entities.Especialidad;
import org.springframework.data.repository.CrudRepository;

public interface EspecialRepository extends CrudRepository<Especialidad, Long> {

   //List<Doctor> findByEspecialidad(Especialidad especialidad);
}
