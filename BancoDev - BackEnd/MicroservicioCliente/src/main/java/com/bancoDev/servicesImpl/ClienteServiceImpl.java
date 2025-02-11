package com.bancoDev.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.Request.ClienteCrearRequest;
import com.bancoDev.DTOs.Response.ClienteResponse;
import com.bancoDev.models.ClienteEntity;
import com.bancoDev.models.HistorialEntity;
import com.bancoDev.repositories.ClienteRepository;
import com.bancoDev.services.ClienteService;
import com.bancoDev.services.HistorialService;
import com.bancoDev.utils.mapper.ClienteMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository _clienteRepository;
    private final HistorialService _historialService;

    @Override
    public ApiResponse<List<ClienteResponse>> listarClientesActivos() {
        List<ClienteEntity> listadoClientes = _clienteRepository.findByActivo(true);
        if(listadoClientes.isEmpty()){
            return ApiResponse.<List<ClienteResponse>>builder()
            .message("Ningun registro encontrado")
            .data(new ArrayList<>())
            .status(false)
            .build();
        }
        List<ClienteResponse> clienteMappeado = ClienteMapper.listadoClienteEntityToListadoClienteResponse(listadoClientes);
        return ApiResponse.<List<ClienteResponse>>builder()
            .message("Datos encontrados correctamente")
            .data(clienteMappeado)
            .status(true)
            .build();
    }

    @Override
    public ApiResponse<ClienteResponse> buscarClientePorId(Long id) {        
        ClienteEntity clienteEncontrado = _clienteRepository.findByIdActivo(id);
        if(clienteEncontrado == null){
            return ApiResponse.<ClienteResponse>builder()
            .message("Ningun registro encontrado")
            .data(new ClienteResponse())
            .status(false)
            .build();
        }
        return ApiResponse.<ClienteResponse>builder()
        .message("Registro encontrado correctamente")
        .data(ClienteMapper.clienteEntityToClienteResponse(clienteEncontrado))
        .status(true)
        .build();
    }

    @Override
    public ApiResponse<ClienteResponse> buscarClientePorCorreoPassword(String correo, String password) {
        ClienteEntity clienteEncontrado = _clienteRepository.findByCorreoIgnoreCaseAndContraseniaIgnoreCase(correo, password);
        if(clienteEncontrado == null){
            return ApiResponse.<ClienteResponse>builder()
            .message("Cliente no encontrado")
            .data(new ClienteResponse())
            .status(false)
            .build();
        }
        return ApiResponse.<ClienteResponse>builder()
            .message("Cliente encontrado correctamente")
            .data(ClienteMapper.clienteEntityToClienteResponse(clienteEncontrado))
            .status(true)
            .build();
    }

    @Override
    @Transactional
    public ApiResponse<ClienteResponse> crearCliente(ClienteCrearRequest cliente) {
        ClienteEntity clienteEntity = ClienteMapper.clienteCrearToClienteEntity(cliente);
        ClienteEntity clienteCorreo = _clienteRepository.findByCorreoIgnoreCase(clienteEntity.getCorreo());
        ClienteEntity clienteDni = _clienteRepository.findByDniIgnoreCase(clienteEntity.getDni());
        if(clienteDni != null) {
            return ApiResponse.<ClienteResponse>builder()
            .message("El DNI ya se encuentra registrado")
            .data(new ClienteResponse())
            .status(false)
            .build();
        }
        if(clienteCorreo != null) {
            return ApiResponse.<ClienteResponse>builder()
            .message("El CORREO ya se encuentra registrado")
            .data(new ClienteResponse())
            .status(false)
            .build();
        }
        try{
            ClienteEntity clienteGuardado = _clienteRepository.save(ClienteMapper.clienteCrearToClienteEntity(cliente));

            //Creacion y setteo de datos al historial
            HistorialEntity historial = new HistorialEntity();
            historial.setScoreCrediticio(3);
            historial.setObservaciones("Ninguna");
            historial.setIdCliente(clienteGuardado);
            _historialService.crearHistorial(historial);

            return ApiResponse.<ClienteResponse>builder()
            .message("Cliente registrado correctamente")
            .data(ClienteMapper.clienteEntityToClienteResponse(clienteGuardado))
            .status(true)
            .build();
        }
        catch (IllegalArgumentException iae) {
            return ApiResponse.<ClienteResponse>builder()
            .message(iae.getMessage())
            .data(new ClienteResponse())
            .status(false)
            .build();
        }
    }

    @Override
    @Transactional
    public ApiResponse<ClienteResponse> actualizarCliente(ClienteEntity cliente) {
        try{
            ClienteEntity clienteActualizado = _clienteRepository.save(cliente);
            return ApiResponse.<ClienteResponse>builder()
            .message("Cliente actualizado correctamente")
            .data(ClienteMapper.clienteEntityToClienteResponse(clienteActualizado))
            .status(true)
            .build();
        }
        catch(IllegalArgumentException iae){
            return ApiResponse.<ClienteResponse>builder()
            .message(iae.getMessage())
            .data(new ClienteResponse())
            .status(true)
            .build();
        }
    }

    @Override
    @Transactional
    public ApiResponse<ClienteResponse> eliminarCliente(Long id) {
        
        if(id == null){
            return ApiResponse.<ClienteResponse>builder()
            .message("El id no debe ser nulo")
            .data(new ClienteResponse())
            .status(false)
            .build();
        }

        ApiResponse<ClienteResponse> clienteEncontrado = buscarClientePorId(id);
        if (clienteEncontrado.isStatus() == false) return clienteEncontrado;
        ClienteEntity cliente = _clienteRepository.findById(id).orElse(null);
        cliente.setActivo(false);
        _clienteRepository.save(cliente);
        return ApiResponse.<ClienteResponse>builder()
        .message("Cliente eliminado correctamente")
        .data(clienteEncontrado.getData())
        .status(true)
        .build();
            

    }

}
