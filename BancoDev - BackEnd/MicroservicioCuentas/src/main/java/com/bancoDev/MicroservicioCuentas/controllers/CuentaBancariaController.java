package com.bancoDev.MicroservicioCuentas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoDev.MicroservicioCuentas.DTOs.ApiResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.request.TransferirDineroRequest;
import com.bancoDev.MicroservicioCuentas.DTOs.response.CuentaResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.response.TransferenciaResponse;
import com.bancoDev.MicroservicioCuentas.services.CuentaBancariaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuentaBancaria")
public class CuentaBancariaController {

    private final CuentaBancariaService _cuentaService;

    @GetMapping("/detalle/{idCliente}")
    public ResponseEntity<ApiResponse<CuentaResponse>> verEstadoCuenta(@PathVariable int idCliente){
        ApiResponse<CuentaResponse> respuesta = _cuentaService.verEstadoCuenta(idCliente);
        if(respuesta.isStatus() == false) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PutMapping("/transferirDinero")
    public ResponseEntity<ApiResponse<TransferenciaResponse>> transferirDinero(@RequestBody TransferirDineroRequest transferir){
        ApiResponse<TransferenciaResponse> respuesta = _cuentaService.transferirDinero(transferir);
        if(respuesta.isStatus() == false) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PostMapping("/crear/{idCliente}")
    public ResponseEntity<ApiResponse<CuentaResponse>> crearCuenta(int idCliente){
        ApiResponse<CuentaResponse> respuesta = _cuentaService.crearCuenta(idCliente);
        if(respuesta.isStatus() == false) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
