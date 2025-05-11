package com.bancoDev.client.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.client.models.EmpleadoSimpleResponse;

@FeignClient(name = "MicroservicioEmpleado")
public interface EmpleadoClient {

    @GetMapping("empleado/buscarNombresPorId/{id}")
    ApiResponse<EmpleadoSimpleResponse> mostrarNombreEmpleadoPorId(@PathVariable Long id);

}
