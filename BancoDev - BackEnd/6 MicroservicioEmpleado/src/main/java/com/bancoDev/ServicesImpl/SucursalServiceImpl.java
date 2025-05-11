package com.bancoDev.ServicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.Models.SucursalEntity;
import com.bancoDev.Repositories.SucursalRepository;
import com.bancoDev.Services.SucursalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository _sucursalRepository;

    @Override
    public ApiResponse<List<SucursalEntity>> listadoSucursales() {
        List<SucursalEntity> listadoSucursal = _sucursalRepository.findAll();
        if(listadoSucursal.isEmpty()){
            return ApiResponse.<List<SucursalEntity>>builder()
            .message("Ningun registro encontrado")
            .data(listadoSucursal)
            .status(false)
            .build();
        }
        return ApiResponse.<List<SucursalEntity>>builder()
        .message("Registros encontrados correctamente")
        .data(listadoSucursal)
        .status(true)
        .build();
    }

}
