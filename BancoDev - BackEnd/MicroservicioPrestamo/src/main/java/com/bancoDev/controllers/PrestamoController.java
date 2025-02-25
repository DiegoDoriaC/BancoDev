package com.bancoDev.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.request.PrestamoCrearDto;
import com.bancoDev.DTOs.response.PrestamoResponse;
import com.bancoDev.services.PrestamoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prestamo")
public class PrestamoController {

    private final PrestamoService _prestamoService;

    @GetMapping("/listarTodosLosPrestamos/{idCliente}")
    public ResponseEntity<ApiResponse<List<PrestamoResponse>>> listarTodosLosPrestamos(@PathVariable Long idCliente){
        ApiResponse<List<PrestamoResponse>> respuesta = _prestamoService.listarTodosLosPrestamos(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/listarPrestamosPagados/{idCliente}")
    public ResponseEntity<ApiResponse<List<PrestamoResponse>>> listarPrestamosPagados(@PathVariable Long idCliente){
        ApiResponse<List<PrestamoResponse>> respuesta = _prestamoService.listarPrestamosPagados(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/listarPrestamosPendientes/{idCliente}")
    public ResponseEntity<ApiResponse<List<PrestamoResponse>>> listarPrestamosPendientes(@PathVariable Long idCliente){
        ApiResponse<List<PrestamoResponse>> respuesta = _prestamoService.listarPrestamosPendientes(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
    
    @GetMapping("/buscarPrestamosPorDniCliente/{dni}")
    public ResponseEntity<ApiResponse<List<PrestamoResponse>>> buscarPrestamosPorDniCliente(String dni){
        ApiResponse<List<PrestamoResponse>> respuesta = _prestamoService.buscarPrestamosPorDniCliente(dni);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/buscarPrestamo/{id}")
    public ResponseEntity<ApiResponse<PrestamoResponse>> buscarPrestamo(@PathVariable Long id){
        ApiResponse<PrestamoResponse> respuesta = _prestamoService.buscarPrestamo(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PostMapping("/crearPrestamo")
    public ResponseEntity<ApiResponse<PrestamoResponse>> crearPrestamo(@RequestBody PrestamoCrearDto prestamo) {
        ApiResponse<PrestamoResponse> respuesta = _prestamoService.crearPrestamo(prestamo);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}