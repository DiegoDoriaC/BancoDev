package com.bancoDev.DTOs.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrestamoResponse {

    private int id;
    private double monto;
    private double tazaInteres;
    private String plazoMeses;
    private LocalDate fechaAprobacion;
    private String estado;
    private String nombresEmpleado;
    private String nombresCliente;

}
