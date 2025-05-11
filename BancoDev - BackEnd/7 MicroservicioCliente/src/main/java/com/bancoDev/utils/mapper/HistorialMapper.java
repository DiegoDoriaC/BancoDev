package com.bancoDev.utils.mapper;

import com.bancoDev.DTOs.Response.HistorialResponse;
import com.bancoDev.models.HistorialEntity;

public class HistorialMapper {

    public static HistorialResponse historialEntityToHistorialResponse(HistorialEntity entity){
        return HistorialResponse.builder()
        .id(entity.getId())
        .scoreCrediticio(entity.getScoreCrediticio())
        .observaciones(entity.getObservaciones())
        .idCliente(entity.getIdCliente().getId())
        .build();
    }

}
