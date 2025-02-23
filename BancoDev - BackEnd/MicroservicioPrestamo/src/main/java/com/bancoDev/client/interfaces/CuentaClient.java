package com.bancoDev.client.interfaces;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.client.models.CuentaResponse;

@FeignClient(name = "MicroservicioCuentas")
public interface CuentaClient {

    @PutMapping("cuenta/disminuirCuenta")
    ApiResponse<CuentaResponse> disminuirCuenta(@RequestParam String idCuenta,@RequestParam BigDecimal montoDinero);

}
