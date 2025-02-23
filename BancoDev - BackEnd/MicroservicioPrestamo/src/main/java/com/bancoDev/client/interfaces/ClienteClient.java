package com.bancoDev.client.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.client.models.ClienteResponse;
import com.bancoDev.client.models.ClienteSimpleResponse;

@FeignClient(name = "MicroservicioCliente")
public interface ClienteClient {

    @GetMapping("cliente/buscarNombresPorId/{id}")
    ApiResponse<ClienteSimpleResponse> mostrarNombreClientePorId(@PathVariable Long id);

    @GetMapping("cliente/buscarPorDni/{dni}")
    ApiResponse<ClienteResponse> buscarPorDni(@PathVariable String dni);

}
