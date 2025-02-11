package com.bancoDev.MicroservicioCuentas.DTOs.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferirDineroRequest {

    private int clienteId;
    private BigDecimal monto;
    private String cuentaDestino;

}
