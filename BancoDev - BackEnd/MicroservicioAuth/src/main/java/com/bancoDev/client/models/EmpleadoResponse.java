package com.bancoDev.client.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String foto;
    private Roles rol;

}
