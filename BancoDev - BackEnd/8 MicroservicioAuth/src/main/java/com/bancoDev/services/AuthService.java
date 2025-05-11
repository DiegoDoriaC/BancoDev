package com.bancoDev.services;

import com.bancoDev.client.models.ClienteCrearRequest;
import com.bancoDev.client.models.ClienteResponse;
import com.bancoDev.models.ApiResponse;
import com.bancoDev.models.AuthResponse;

public interface AuthService {

    ApiResponse<AuthResponse> iniciarSesion(String correo, String password);
    ApiResponse<ClienteResponse> crearCuenta(ClienteCrearRequest cliente);

}
