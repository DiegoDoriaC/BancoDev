package com.bancoDev.DTOs.request;

import java.math.BigDecimal;

import com.bancoDev.models.enums.TipoPago;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagoRealizarRequest {

    private int pagoId;
    private BigDecimal montoPagado;
    private TipoPago tipoPago;
    private int empleadoId;

}
