package com.minsait.consultas.clients;

import com.minsait.consultas.model.entity.PacienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(value = "msvc-pacientes",url = "http://localhost:8080/v1/api/pacientes",dismiss404 = true)
public interface PacientesClient {

    @GetMapping("/obtienepacienteid/{id}")
    public Optional<PacienteDTO> findById (@PathVariable Long id);


}