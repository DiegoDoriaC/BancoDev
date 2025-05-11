package com.bancoDev.client.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.client.models.CuentaResponse;

@FeignClient(name = "MicroservicioCuentas")
public interface CuentaClient {

    @PostMapping("/cuentaBancaria/crear/{idCliente}")
    ApiResponse<CuentaResponse> crearCuenta(@PathVariable int idCliente);

}
