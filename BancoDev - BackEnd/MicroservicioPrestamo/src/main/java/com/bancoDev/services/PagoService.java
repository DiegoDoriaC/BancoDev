package com.bancoDev.services;

import java.util.List;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.request.PagoCrearRequest;
import com.bancoDev.DTOs.request.PagoRealizarRequest;
import com.bancoDev.DTOs.response.PagoResponse;

public interface PagoService {

    ApiResponse<List<PagoResponse>> listarTodosLosPagos(Long idPrestamo);
    ApiResponse<List<PagoResponse>> listarPagosPagados(Long idPrestamo);
    ApiResponse<List<PagoResponse>> listarPagosPendiendes(Long idPrestamo);
    ApiResponse<PagoResponse> buscarPago(Long id);
    ApiResponse<PagoResponse> realizarPago(PagoRealizarRequest pagoRealizar);

    //Metodo propio del servicio no expuesto desde el controlador
    ApiResponse<List<PagoResponse>> crearPago(PagoCrearRequest pagoCrear);

}
