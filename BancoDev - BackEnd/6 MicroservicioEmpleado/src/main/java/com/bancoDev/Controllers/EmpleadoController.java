package com.bancoDev.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.Response.EmpleadoResponse;
import com.bancoDev.DTOs.Response.EmpleadoSimpleResponse;
import com.bancoDev.Services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/empleado")
public class EmpleadoController {

    private final EmpleadoService _empleadoService;

    @GetMapping("listarPorSucursalId/{idSucursal}")
    public ResponseEntity<ApiResponse<List<EmpleadoResponse>>> listarEmpleadoPorIdSucursal(@PathVariable Long idSucursal){
        ApiResponse<List<EmpleadoResponse>> respuesta = _empleadoService.listarEmpleadoPorIdSucursal(idSucursal);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<ApiResponse<EmpleadoResponse>> buscarPorId(@PathVariable Long id) {
        ApiResponse<EmpleadoResponse> respuesta = _empleadoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("buscarNombresPorId/{id}")
    public ResponseEntity<ApiResponse<EmpleadoSimpleResponse>> mostrarNombreEmpleadoPorId(@PathVariable Long id) {
        ApiResponse<EmpleadoSimpleResponse> respuesta = _empleadoService.mostrarNombreEmpleadoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("buscarPorCorreoPassword")
    public ResponseEntity<ApiResponse<EmpleadoResponse>> buscarPorCorreoPassword(@RequestParam String correo, @RequestParam String password) {
        ApiResponse<EmpleadoResponse> respuesta = _empleadoService.buscarPorCorreoPassword(correo, password);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
