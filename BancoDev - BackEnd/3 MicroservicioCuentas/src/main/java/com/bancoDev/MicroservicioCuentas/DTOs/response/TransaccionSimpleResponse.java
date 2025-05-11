package com.bancoDev.MicroservicioCuentas.DTOs.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransaccionSimpleResponse {

    private String numeroTransaccion;
    private BigDecimal monto;
    private String fecha;

}
