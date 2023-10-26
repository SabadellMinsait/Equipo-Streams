package com.minsait.consultas.clients;

import com.minsait.consultas.model.entity.Paciente;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(value = "msvc-pacientes",url = "http://localhost:8080/v1/api/pacientes")
public interface PacientesClient {

    @GetMapping("/obtienepacienteid/{id}")
    public Optional<Paciente> findById (@PathVariable Long id);


}