package com.bancoDev.services;

import java.util.List;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.request.PrestamoCrearDto;
import com.bancoDev.DTOs.response.PrestamoResponse;

public interface PrestamoService {

    ApiResponse<List<PrestamoResponse>> listarTodosLosPrestamos(Long idCliente);
    ApiResponse<List<PrestamoResponse>> listarPrestamosPagados(Long idCliente);
    ApiResponse<List<PrestamoResponse>> listarPrestamosPendientes(Long idCliente);
    ApiResponse<List<PrestamoResponse>> listarPrestamosPendientesEmpleado(Long idEmpleado);
    ApiResponse<List<PrestamoResponse>> buscarPrestamosPorDniCliente(String dni);
    ApiResponse<List<PrestamoResponse>> buscarPrestamosPorDniEmpleado(String dni);
    ApiResponse<PrestamoResponse> buscarPrestamo(Long id);
    ApiResponse<PrestamoResponse> crearPrestamo(Long id);
    ApiResponse<PrestamoResponse> solicitarPrestamo(PrestamoCrearDto prestamo);
    

}
