package com.bancoDev.services;

import java.util.List;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.Request.ClienteCrearRequest;
import com.bancoDev.DTOs.Response.ClienteResponse;
import com.bancoDev.models.ClienteEntity;

public interface ClienteService {

    ApiResponse<List<ClienteResponse>> listarClientesActivos();
    ApiResponse<ClienteResponse> buscarClientePorId(Long id);
    ApiResponse<ClienteResponse> buscarClientePorCorreoPassword(String correo, String password);
    ApiResponse<ClienteResponse> crearCliente(ClienteCrearRequest cliente);
    ApiResponse<ClienteResponse> actualizarCliente(ClienteEntity cliente);
    ApiResponse<ClienteResponse> eliminarCliente(Long id);

}
