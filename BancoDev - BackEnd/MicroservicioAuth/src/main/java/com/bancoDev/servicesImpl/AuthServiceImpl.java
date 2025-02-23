package com.bancoDev.servicesImpl;

import org.springframework.stereotype.Service;

import com.bancoDev.client.interfaces.ClienteClient;
import com.bancoDev.client.interfaces.EmpleadoClient;
import com.bancoDev.client.models.ClienteCrearRequest;
import com.bancoDev.client.models.ClienteResponse;
import com.bancoDev.client.models.EmpleadoResponse;
import com.bancoDev.jwt.JwtUtils;
import com.bancoDev.models.ApiResponse;
import com.bancoDev.models.AuthResponse;
import com.bancoDev.services.AuthService;
import com.bancoDev.utils.EncriptarSHA256;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final JwtUtils _jwtUtils;
    private final ClienteClient _clenteClient;
    private final EmpleadoClient _empleadoClient;

    @Override
    public ApiResponse<AuthResponse> iniciarSesion(String correo, String password) {    
        // Primero buscara en la base de datos del empleado    
        ApiResponse<EmpleadoResponse> empleadoEncontrado = _empleadoClient.buscarPorCorreoPassword(correo, EncriptarSHA256.encriptar(password));
        if(empleadoEncontrado.isStatus()){
            String jwt = _jwtUtils.generarToken(correo, empleadoEncontrado.getData().getRol().toString());
            AuthResponse authResponse = AuthResponse.builder()
                .id(empleadoEncontrado.getData().getId())
                .nombres(empleadoEncontrado.getData().getNombre())
                .apellidos(empleadoEncontrado.getData().getApellido())
                .correo(empleadoEncontrado.getData().getCorreo())
                .rol(empleadoEncontrado.getData().getRol().toString())
                .token(jwt)
                .build();
            return ApiResponse.<AuthResponse>builder()
            .message(empleadoEncontrado.getMessage())
            .data(authResponse)
            .status(true)
            .build();
        }
        // Si no encuentra buscara en la base de datos de los clientes
        ApiResponse<ClienteResponse> clienteEncontrado = _clenteClient.buscarClientePorCorreoPassword(correo, EncriptarSHA256.encriptar(password));
        if(clienteEncontrado.isStatus()){
            String jwt = _jwtUtils.generarToken(correo, clienteEncontrado.getData().getRol().toString());
            AuthResponse authResponse = AuthResponse.builder()
                .id(clienteEncontrado.getData().getId())
                .nombres(clienteEncontrado.getData().getNombres())
                .apellidos(clienteEncontrado.getData().getApellidos())
                .correo(clienteEncontrado.getData().getCorreo())
                .rol(clienteEncontrado.getData().getRol().toString())
                .token(jwt)
                .build();
            return ApiResponse.<AuthResponse>builder()
            .message(clienteEncontrado.getMessage())
            .data(authResponse)
            .status(true)
            .build();
        }
        //En el caso no encuentre a nadie que coincida en la base de datos
        return ApiResponse.<AuthResponse>builder()
        .message("Ninguna coincidencia encontrada, por favor digite correctamente sus credenciales")
        .data(null)
        .status(false)
        .build();
    }
 
    @Override
    public ApiResponse<ClienteResponse> crearCuenta(ClienteCrearRequest cliente){
        // Encriptación de la contraseña
        String contraseniaCodificada = EncriptarSHA256.encriptar(cliente.getContrasenia());
        ClienteCrearRequest clienteToCrear = cliente;
        clienteToCrear.setContrasenia(contraseniaCodificada);
        
        // Esperar la creación del cliente antes de continuar
        ApiResponse<ClienteResponse> clienteCreado = _clenteClient.crearCliente(clienteToCrear);
        return ApiResponse.<ClienteResponse>builder()
        .message(clienteCreado.getMessage())
        .data(clienteCreado.getData())
        .status(clienteCreado.isStatus())
        .build();
        
    }

}


