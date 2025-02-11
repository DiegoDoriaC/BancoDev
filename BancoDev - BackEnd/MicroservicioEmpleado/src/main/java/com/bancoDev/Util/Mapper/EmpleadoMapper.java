package com.bancoDev.Util.Mapper;

import com.bancoDev.DTOs.Response.EmpleadoResponse;
import com.bancoDev.Models.EmpleadoEntity;

public class EmpleadoMapper {

    public static EmpleadoResponse empleadoEntityToEmpleadoResponse(EmpleadoEntity empleado){

        return EmpleadoResponse.builder()
        .id(empleado.getId())
        .nombre(empleado.getNombre())
        .apellido(empleado.getApellido())
        .correo(empleado.getCorreo())
        .foto(empleado.getFoto())
        .rol(empleado.getRol())
        .build();

    }

}
