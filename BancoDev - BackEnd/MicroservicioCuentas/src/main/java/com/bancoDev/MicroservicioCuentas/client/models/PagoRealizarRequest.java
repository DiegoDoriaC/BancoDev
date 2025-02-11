package com.bancoDev.MicroservicioCuentas.client.models;

import java.math.BigDecimal;

import com.bancoDev.MicroservicioCuentas.client.models.enums.TipoPago;

import lombok.Data;

@Data
public class PagoRealizarRequest {

    private int pagoId;
    private BigDecimal montoPagado;
    private TipoPago tipoPago;
    private int empleadoId;

}

