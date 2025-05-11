package com.bancoDev.MicroservicioCuentas.DTOs.request;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagarCuentaRequest {

    private int clienteId;
    private BigDecimal monto;

}
