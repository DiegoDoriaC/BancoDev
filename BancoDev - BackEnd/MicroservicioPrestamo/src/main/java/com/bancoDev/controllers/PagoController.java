package com.bancoDev.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.request.PagoRealizarRequest;
import com.bancoDev.DTOs.request.RealizarPagoTransaccionRequest;
import com.bancoDev.DTOs.response.PagoResponse;
import com.bancoDev.services.PagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pago")
public class PagoController {

    private final PagoService _pagoService;

    @GetMapping("/listarTodosLosPagos/{idPrestamo}")
    public ResponseEntity<ApiResponse<List<PagoResponse>>> listarTodosLosPagos(@PathVariable Long idPrestamo){
        ApiResponse<List<PagoResponse>> respuesta = _pagoService.listarTodosLosPagos(idPrestamo);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/listarPagosPagados/{idPrestamo}")
    public ResponseEntity<ApiResponse<List<PagoResponse>>> listarPagosPagados(@PathVariable Long idPrestamo){
        ApiResponse<List<PagoResponse>> respuesta = _pagoService.listarPagosPagados(idPrestamo);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/listarPagosPendiendes/{idPrestamo}")
    public ResponseEntity<ApiResponse<List<PagoResponse>>> listarPagosPendiendes(@PathVariable Long idPrestamo){
        ApiResponse<List<PagoResponse>> respuesta = _pagoService.listarPagosPendiendes(idPrestamo);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/buscarPago/{id}")
    public ResponseEntity<ApiResponse<PagoResponse>> buscarPago(@PathVariable Long id){
        ApiResponse<PagoResponse> respuesta = _pagoService.buscarPago(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PutMapping("/realizarPago")
    public ResponseEntity<ApiResponse<PagoResponse>> realizarPago(@RequestBody PagoRealizarRequest pagoRealizar){
        ApiResponse<PagoResponse> respuesta = _pagoService.realizarPago(pagoRealizar);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PutMapping("/realizarPagoTransaccion")
    public ResponseEntity<ApiResponse<PagoResponse>> realizarPagoTransaccion(@RequestBody RealizarPagoTransaccionRequest pagoRealizar){
        ApiResponse<PagoResponse> respuesta = _pagoService.realizarPagoTransaccion(pagoRealizar);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
