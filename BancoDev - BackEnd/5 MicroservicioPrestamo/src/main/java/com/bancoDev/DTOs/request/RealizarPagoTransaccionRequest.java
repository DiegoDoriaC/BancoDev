package com.bancoDev.DTOs.request;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RealizarPagoTransaccionRequest {

    private int pagoId;
    private BigDecimal montoPagado;

}
