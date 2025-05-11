package com.bancoDev.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancoDev.client.models.ClienteCrearRequest;
import com.bancoDev.client.models.ClienteResponse;
import com.bancoDev.models.ApiResponse;
import com.bancoDev.models.AuthResponse;
import com.bancoDev.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService _authService;

    @GetMapping("/iniciarSesion")
    public ResponseEntity<ApiResponse<AuthResponse>> iniciarSesion(@RequestParam String correo, @RequestParam String password){
        ApiResponse<AuthResponse> respuesta = _authService.iniciarSesion(correo, password);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
    
    @PostMapping("/crearCuenta")
    public ResponseEntity<ApiResponse<ClienteResponse>> crearCuenta (@RequestBody ClienteCrearRequest cliente){
        ApiResponse<ClienteResponse> respuesta = _authService.crearCuenta(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


}
