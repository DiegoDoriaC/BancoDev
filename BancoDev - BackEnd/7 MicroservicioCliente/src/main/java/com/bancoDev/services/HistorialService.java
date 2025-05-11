package com.bancoDev.services;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.Response.HistorialResponse;
import com.bancoDev.models.HistorialEntity;

public interface HistorialService {

    public ApiResponse<HistorialResponse> buscarHistorialPorIdCliente(Long id);
    public ApiResponse<HistorialResponse> crearHistorial(HistorialEntity historial);    

}