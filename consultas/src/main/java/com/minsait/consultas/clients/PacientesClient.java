package com.minsait.consultas.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "msvc-pacientes",url = "http://localhost")
public interface PacientesClient {
}
