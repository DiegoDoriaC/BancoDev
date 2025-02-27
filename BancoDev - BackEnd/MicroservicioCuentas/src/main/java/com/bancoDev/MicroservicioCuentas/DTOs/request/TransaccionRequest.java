package com.bancoDev.MicroservicioCuentas.DTOs.request;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransaccionRequest {

    private BigDecimal monto;
    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;

}
