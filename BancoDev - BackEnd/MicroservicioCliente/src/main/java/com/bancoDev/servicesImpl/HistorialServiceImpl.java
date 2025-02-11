package com.bancoDev.servicesImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.Response.HistorialResponse;
import com.bancoDev.models.HistorialEntity;
import com.bancoDev.repositories.HistorialRepositoy;
import com.bancoDev.services.HistorialService;
import com.bancoDev.utils.mapper.HistorialMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistorialServiceImpl implements HistorialService {

    private final HistorialRepositoy _historialRepositoy;

    @Override
    public ApiResponse<HistorialResponse> buscarHistorialPorIdCliente(Long id) {
        HistorialEntity historialOptional = _historialRepositoy.findByIdCliente_Id(id);
        if(historialOptional == null){
            return ApiResponse.<HistorialResponse>builder()
            .message("Historial no encontrado")
            .data(new HistorialResponse())
            .status(false)
            .build();
        }
        return ApiResponse.<HistorialResponse>builder()
        .message("Historial encontrado correctamente")
        .data(HistorialMapper.historialEntityToHistorialResponse(historialOptional))
        .status(true)
        .build();

    }

    @Override
    @Transactional
    public ApiResponse<HistorialResponse> crearHistorial(HistorialEntity historial) {
        ApiResponse<HistorialResponse> historialResponse = new ApiResponse<HistorialResponse>();
        try{
            HistorialEntity historialCreado = _historialRepositoy.save(historial);
            historialResponse.setMessage("Historial creado correctamente");
            historialResponse.setData(HistorialMapper.historialEntityToHistorialResponse(historialCreado));
            historialResponse.setStatus(true);
            return historialResponse;
        }
        catch (IllegalArgumentException iae) {
            historialResponse.setMessage(iae.getMessage());
            historialResponse.setData(new HistorialResponse());
            historialResponse.setStatus(false);
            return historialResponse;
        }
    }

}