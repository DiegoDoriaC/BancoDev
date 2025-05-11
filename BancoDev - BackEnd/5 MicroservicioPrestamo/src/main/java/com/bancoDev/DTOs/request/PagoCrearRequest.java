package com.bancoDev.DTOs.request;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagoCrearRequest {

    private int prestamoId;
    private int numeroCoutas;
    private BigDecimal montoPago;

}
