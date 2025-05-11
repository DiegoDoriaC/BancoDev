package com.bancoDev.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialResponse {
    
    private Long id;
    private int scoreCrediticio;
    private String observaciones;
    private Long idCliente;

}
