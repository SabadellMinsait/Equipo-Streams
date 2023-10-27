package com.minsait.pacientes.clients;

import com.minsait.pacientes.model.HistorialDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value="msvc-consultas", url = "http://localhost:8091/v1/api")
public interface ConsultaClient {

    @GetMapping("/historial-paciente/{id}")
    public ResponseEntity<HistorialDTO> findHistorialMedicoByPacienteID(@NotNull @PathVariable Long id);

}
