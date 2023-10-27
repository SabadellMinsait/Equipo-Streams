package com.minsait.consultas.clients;

import com.minsait.consultas.model.entity.DoctorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@FeignClient(value = "msvc-doctores",url = "http://localhost:8092/doctor",dismiss404 = true)
public interface DoctoresClient {
    @GetMapping("/{id}")
    public Optional<DoctorDTO> obtenerDoctor(@PathVariable Long id);

}
