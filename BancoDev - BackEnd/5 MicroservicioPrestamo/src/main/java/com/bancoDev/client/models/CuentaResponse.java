package com.bancoDev.client.models;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class CuentaResponse {

    private String numeroCuenta;
    private BigDecimal montoDinero;

}
