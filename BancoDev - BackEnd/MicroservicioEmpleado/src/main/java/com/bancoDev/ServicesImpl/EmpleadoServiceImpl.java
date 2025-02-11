package com.bancoDev.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.Response.EmpleadoResponse;
import com.bancoDev.Models.EmpleadoEntity;
import com.bancoDev.Repositories.EmpleadoRepository;
import com.bancoDev.Services.EmpleadoService;
import com.bancoDev.Util.Mapper.EmpleadoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    public final EmpleadoRepository _empleadoRepository;

    @Override
    public ApiResponse<List<EmpleadoResponse>> listarEmpleadoPorIdSucursal(Long idSucursal) {
        try{
            List<EmpleadoEntity> lisadoEmpleado = _empleadoRepository.findByIdSucursal_Id(idSucursal);
            if(lisadoEmpleado.isEmpty()){
                return ApiResponse.<List<EmpleadoResponse>>builder()
                .message("Ningun empleado encontrado")
                .data(new ArrayList<>())
                .status(false)
                .build();
            }
            List<EmpleadoResponse> listaEmpleadoMapeado = lisadoEmpleado.stream().map(item -> {
                return EmpleadoMapper.empleadoEntityToEmpleadoResponse(item);
            }).toList();

            return ApiResponse.<List<EmpleadoResponse>>builder()
            .message("Registros encontrados correctamente")
            .data(listaEmpleadoMapeado)
            .status(true)
            .build();
        }
        catch (Exception e) {
            return ApiResponse.<List<EmpleadoResponse>>builder()
                .message(e.getMessage())
                .data(new ArrayList<>())
                .status(false)
                .build();
        }
    }

    @Override
    public ApiResponse<EmpleadoResponse> buscarPorId(Long id) {
        Optional<EmpleadoEntity> empleadoOptional = _empleadoRepository.findById(id);
        if(empleadoOptional.isEmpty()){
            return ApiResponse.<EmpleadoResponse>builder()
            .message("Empleado no encontrado")
            .data(null)
            .status(false)
            .build();
        }
        return ApiResponse.<EmpleadoResponse>builder()
            .message("Empleado encontrado correctamente")
            .data(EmpleadoMapper.empleadoEntityToEmpleadoResponse(empleadoOptional.get()))
            .status(true)
            .build();
    }

    @Override
    public ApiResponse<EmpleadoResponse> buscarPorCorreoPassword(String correo, String password) {
        EmpleadoEntity empleadoEncontrado = _empleadoRepository.findByCorreoAndPassword(correo, password);
        if(empleadoEncontrado == null){
            return ApiResponse.<EmpleadoResponse>builder()
            .message("Credenciales incorrectas")
            .data(null)
            .status(false)
            .build();
        }
        return ApiResponse.<EmpleadoResponse>builder()
            .message("Credenciales validadas correctamente")
            .data(EmpleadoMapper.empleadoEntityToEmpleadoResponse(empleadoEncontrado))
            .status(true)
            .build();
    }

}
