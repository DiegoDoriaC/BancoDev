package com.bancoDev.MicroservicioCuentas.models;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.bancoDev.MicroservicioCuentas.models.enums.Operacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "tbl_transacciones")
public class TransaccionEntity {

    @Id
    @Field("_id")
    private String numeroTransaccion;
    private BigDecimal monto;

    private String fecha;

    private Operacion operacion;
    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;

}
