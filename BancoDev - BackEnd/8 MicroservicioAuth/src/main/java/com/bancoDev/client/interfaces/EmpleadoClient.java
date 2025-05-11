package com.bancoDev.client.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bancoDev.client.models.EmpleadoResponse;
import com.bancoDev.models.ApiResponse;

@FeignClient(name = "MicroservicioEmpleado")
public interface EmpleadoClient {

    @GetMapping("/empleado/buscarPorCorreoPassword")
    ApiResponse<EmpleadoResponse> buscarPorCorreoPassword(@RequestParam String correo, @RequestParam String password);

}
