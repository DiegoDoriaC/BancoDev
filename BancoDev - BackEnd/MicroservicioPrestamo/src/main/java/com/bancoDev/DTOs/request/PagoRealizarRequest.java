package com.bancoDev.DTOs.request;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagoRealizarRequest {

    private int pagoId;
    private BigDecimal montoPagado;
    private int empleadoId;

}
