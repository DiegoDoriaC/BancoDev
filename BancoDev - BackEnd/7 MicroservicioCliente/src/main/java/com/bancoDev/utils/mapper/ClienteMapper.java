package com.bancoDev.utils.mapper;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.bancoDev.DTOs.Roles;
import com.bancoDev.DTOs.Request.ClienteCrearRequest;
import com.bancoDev.DTOs.Response.ClienteResponse;
import com.bancoDev.models.ClienteEntity;

public class ClienteMapper {

    public static ClienteEntity clienteCrearToClienteEntity(ClienteCrearRequest clienteRequest){

        ClienteEntity clienteMapeado = new ClienteEntity();
        clienteMapeado.setNombres(clienteRequest.getNombres());
        clienteMapeado.setApellidos(clienteRequest.getApellidos());
        clienteMapeado.setDni(clienteRequest.getDni());
        clienteMapeado.setFechaNacimiento(clienteRequest.getFechaNacimiento());
        clienteMapeado.setDirrecion(clienteRequest.getDirrecion());
        clienteMapeado.setCorreo(clienteRequest.getCorreo());
        clienteMapeado.setContrasenia(clienteRequest.getContrasenia());
        clienteMapeado.setActivo(true);        
        clienteMapeado.setRol(Roles.USER);
        return clienteMapeado;

    }
    public static ClienteResponse clienteEntityToClienteResponse(ClienteEntity clienteEntity){

        int edad = Period.between(clienteEntity.getFechaNacimiento(), LocalDate.now()).getYears();

        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(clienteEntity.getId());
        clienteResponse.setNombres(clienteEntity.getNombres());
        clienteResponse.setApellidos(clienteEntity.getApellidos());
        clienteResponse.setDni(clienteEntity.getDni());
        clienteResponse.setEdad(edad);
        clienteResponse.setDirrecion(clienteEntity.getDirrecion());
        clienteResponse.setCorreo(clienteEntity.getCorreo());
        clienteResponse.setRol(clienteEntity.getRol().toString());
        return clienteResponse;

    }
    public static List<ClienteResponse> listadoClienteEntityToListadoClienteResponse(List<ClienteEntity> listadoClienteEntity){

        List<ClienteResponse> listadoCliente = new ArrayList<>();

        for (ClienteEntity clienteEntity : listadoClienteEntity) {
            
            int edad = Period.between(clienteEntity.getFechaNacimiento(), LocalDate.now()).getYears();    
            ClienteResponse clienteResponse = new ClienteResponse();
            clienteResponse.setId(clienteEntity.getId());
            clienteResponse.setNombres(clienteEntity.getNombres());
            clienteResponse.setApellidos(clienteEntity.getApellidos());
            clienteResponse.setDni(clienteEntity.getDni());
            clienteResponse.setEdad(edad);
            clienteResponse.setDirrecion(clienteEntity.getDirrecion());
            clienteResponse.setCorreo(clienteEntity.getCorreo());
            clienteResponse.setRol(clienteEntity.getRol().toString());
            listadoCliente.add(clienteResponse);

        }

        return listadoCliente;

    }

}
