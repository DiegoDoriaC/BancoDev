package com.bancoDev.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.Request.ClienteCrearRequest;
import com.bancoDev.DTOs.Response.ClienteResponse;
import com.bancoDev.DTOs.Response.ClienteSimpleResponse;
import com.bancoDev.models.ClienteEntity;
import com.bancoDev.services.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService _clienteService;

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<List<ClienteResponse>>> listarClientesActivos(){
        ApiResponse<List<ClienteResponse>> respuesta = _clienteService.listarClientesActivos();
        if(respuesta.isStatus() == false) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> buscarClientePorId(@PathVariable Long id){
        ApiResponse<ClienteResponse> respuesta = _clienteService.buscarClientePorId(id);
        if(respuesta.isStatus() == false) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
    
    @GetMapping("/buscarPorDni/{dni}")
    public ResponseEntity<ApiResponse<ClienteResponse>> buscarPorDni(@PathVariable String dni){
        ApiResponse<ClienteResponse> respuesta = _clienteService.buscarPorDni(dni);
        if(respuesta.isStatus() == false) return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/buscarNombresPorId/{id}")
    public ResponseEntity<ApiResponse<ClienteSimpleResponse>> mostrarNombreClientePorId(@PathVariable Long id){
        ApiResponse<ClienteSimpleResponse> respuesta = _clienteService.mostrarNombreClientePorId(id);
        if(respuesta.isStatus() == false) return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/buscarPorCorreoPassword")
    public ResponseEntity<ApiResponse<ClienteResponse>> buscarClientePorCorreoPassword(@RequestParam String correo, @RequestParam String password){
        ApiResponse<ClienteResponse> respuesta = _clienteService.buscarClientePorCorreoPassword(correo, password);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta); 
    }

    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<ClienteResponse>> crearCliente(@RequestBody ClienteCrearRequest cliente){
        ApiResponse<ClienteResponse> respuesta = _clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse<ClienteResponse>> actualizarCliente(@RequestBody ClienteEntity cliente){
        ApiResponse<ClienteResponse> respuesta = _clienteService.actualizarCliente(cliente);
        if(respuesta.isStatus() == false) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> eliminarCliente(@PathVariable Long id){
        ApiResponse<ClienteResponse> respuesta = _clienteService.eliminarCliente(id);
        if(respuesta.isStatus() == false) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }





    
    
}
