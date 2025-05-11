package com.bancoDev.DTOs.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagoResponse {

    private int id;
    private LocalDate fechaPagoEstimada;
    private LocalDate fechaPagoRealizada;
    private double montoPagado;
    private String estado;
    private String nombreEmpleado;
    private int prestamoId;

}
