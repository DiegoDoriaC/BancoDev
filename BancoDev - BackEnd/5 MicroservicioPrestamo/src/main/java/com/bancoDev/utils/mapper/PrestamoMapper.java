package com.bancoDev.utils.mapper;

import com.bancoDev.DTOs.response.PrestamoResponse;
import com.bancoDev.models.PrestamoEntity;

public class PrestamoMapper {

    public static PrestamoResponse prestamoEntiyToPrestamosResponse(PrestamoEntity prestamo){
        return PrestamoResponse.builder()
        .id(prestamo.getId().intValue())
        .monto(prestamo.getMonto().doubleValue())
        .tazaInteres(prestamo.getTazaInteres())
        .plazoMeses(prestamo.getPlazoMeses().toString())
        .fechaAprobacion(prestamo.getFechaAprobacion().toLocalDate())
        .estado(prestamo.getEstado().toString())
        .build();
    }
}
