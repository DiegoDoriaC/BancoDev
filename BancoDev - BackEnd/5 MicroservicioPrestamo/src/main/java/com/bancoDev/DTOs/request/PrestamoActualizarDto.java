package com.bancoDev.DTOs.request;

import java.math.BigDecimal;

import com.bancoDev.models.enums.PlazoMeses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PrestamoActualizarDto {

    private Long idPrestamo;
    private BigDecimal sueldoCliente;
    private double montoSolicitado;
    private PlazoMeses plazoMeses;
    private int empleadoId;
    private int clienteId;
    private int socreCrediticio;

}
