package com.bancoDev.MicroservicioCuentas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoDev.MicroservicioCuentas.DTOs.ApiResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.response.TransaccionCompletaResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.response.TransaccionSimpleResponse;
import com.bancoDev.MicroservicioCuentas.services.TransaccionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaccion")
public class TransaccionController {

    private final TransaccionService _transaccionService;
    
    @GetMapping("/listarTransacciones/{numeroCuenta}")
    public ResponseEntity<ApiResponse<List<TransaccionSimpleResponse>>> listarTransaccion(@PathVariable String numeroCuenta){
        ApiResponse<List<TransaccionSimpleResponse>> respuesta = _transaccionService.listarTransaccion(numeroCuenta);
        if(respuesta.isStatus()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/detalleTransaccion/{id}")
    public ResponseEntity<ApiResponse<TransaccionCompletaResponse>> detalleTransaccion(@PathVariable String id){
        ApiResponse<TransaccionCompletaResponse> respuesta = _transaccionService.detalleTransaccion(id);
        if(respuesta.isStatus()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
