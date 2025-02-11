package com.bancoDev.Services;

import java.util.List;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.Response.EmpleadoResponse;

public interface EmpleadoService {

    ApiResponse<List<EmpleadoResponse>> listarEmpleadoPorIdSucursal(Long idSucursal);
    ApiResponse<EmpleadoResponse> buscarPorId(Long id);
    ApiResponse<EmpleadoResponse> buscarPorCorreoPassword(String correo, String password);

}
