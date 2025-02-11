package com.bancoDev.MicroservicioCuentas.models;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "tbl_cuentas_bancarias")
public class CuentaBancariaEntity {

    @Id
    private String numeroCuenta;
    private BigDecimal montoDinero;
    private int clienteId;

}
