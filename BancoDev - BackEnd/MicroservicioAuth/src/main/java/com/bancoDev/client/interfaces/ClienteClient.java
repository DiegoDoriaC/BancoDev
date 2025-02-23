package com.bancoDev.client.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.bancoDev.client.models.ClienteCrearRequest;
import com.bancoDev.client.models.ClienteResponse;
import com.bancoDev.models.ApiResponse;

@FeignClient(name = "MicroservicioCliente")
public interface ClienteClient {

    @GetMapping("/cliente/buscarPorCorreoPassword")
    ApiResponse<ClienteResponse> buscarClientePorCorreoPassword(@RequestParam String correo, @RequestParam String password);

    @PostMapping("/cliente/crear")
    ApiResponse<ClienteResponse> crearCliente(@RequestBody ClienteCrearRequest cliente);

}
