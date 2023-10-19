package com.minsait.doctores.repository;

import com.minsait.doctores.entities.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctoresRepository extends CrudRepository<Doctor, Long> {
}
