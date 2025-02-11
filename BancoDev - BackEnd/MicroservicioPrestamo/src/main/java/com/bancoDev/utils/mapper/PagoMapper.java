package com.bancoDev.utils.mapper;

import com.bancoDev.DTOs.response.PagoResponse;
import com.bancoDev.models.PagoEntity;

public class PagoMapper {

    public static PagoResponse pagoEntityToPagoResponse(PagoEntity pagoEntity){
        return PagoResponse.builder()
        .id(pagoEntity.getId().intValue())
        .fechaPagoEstimada(pagoEntity.getFechaPagoEstimada())
        .fechaPagoRealizada(pagoEntity.getFechaPagoRealizada().toLocalDate())
        .montoPagado(pagoEntity.getMontoPago().doubleValue())
        .estado(pagoEntity.getEstado().toString())
        .prestamoId(pagoEntity.getPrestamoId().getId().intValue())
        .build();
    }    

}
