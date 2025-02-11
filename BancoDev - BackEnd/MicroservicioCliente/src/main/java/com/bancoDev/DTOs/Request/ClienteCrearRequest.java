package com.bancoDev.DTOs.Request;

import java.time.LocalDate;

import com.bancoDev.DTOs.Roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteCrearRequest {

    private Long id;
    private String nombres;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;
    private String dirrecion;
    private String correo;
    private String contrasenia;
    private Roles rol;

}
