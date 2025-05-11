package com.bancoDev.MicroservicioCuentas.DTOs.request;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecargarCuentaRequest {    

    private String idCuenta;
    private BigDecimal montoDinero;    

}
