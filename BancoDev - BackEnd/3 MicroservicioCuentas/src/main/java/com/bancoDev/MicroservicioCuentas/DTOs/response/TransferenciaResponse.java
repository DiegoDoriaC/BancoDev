package com.bancoDev.MicroservicioCuentas.DTOs.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferenciaResponse {

    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;
    private BigDecimal montoDinero;

}
