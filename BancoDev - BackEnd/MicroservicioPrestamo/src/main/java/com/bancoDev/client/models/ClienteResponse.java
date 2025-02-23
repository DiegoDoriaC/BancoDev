package com.bancoDev.client.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponse {

    private Long id;
    private String nombres;
    private String apellidos;
    private String dni;
    private int edad;
    private String dirrecion;
    private String correo;
    private String rol;

}
