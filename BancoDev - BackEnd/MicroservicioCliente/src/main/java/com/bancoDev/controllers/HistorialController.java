package com.bancoDev.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.Response.HistorialResponse;
import com.bancoDev.services.HistorialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/historial")
public class HistorialController {

    private final HistorialService _historialService;

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<HistorialResponse>> buscarHistorialPorIdCliente(Long id){
        ApiResponse<HistorialResponse> respuesta = _historialService.buscarHistorialPorIdCliente(id);
        if(respuesta.isStatus() == false) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
