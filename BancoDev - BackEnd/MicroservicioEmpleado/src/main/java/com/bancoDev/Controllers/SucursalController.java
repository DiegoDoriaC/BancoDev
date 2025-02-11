package com.bancoDev.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.Models.SucursalEntity;
import com.bancoDev.Services.SucursalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sucursal")
public class SucursalController {

    private final SucursalService _sucursalService;

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<List<SucursalEntity>>> listadoSucursales(){
        ApiResponse<List<SucursalEntity>> response = _sucursalService.listadoSucursales();
        if(response.isStatus() == false) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
