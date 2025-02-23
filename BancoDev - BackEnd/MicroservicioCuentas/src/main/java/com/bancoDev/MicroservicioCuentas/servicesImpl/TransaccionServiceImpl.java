package com.bancoDev.MicroservicioCuentas.servicesImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bancoDev.MicroservicioCuentas.DTOs.ApiResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.request.TransaccionRequest;
import com.bancoDev.MicroservicioCuentas.DTOs.response.TransaccionCompletaResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.response.TransaccionSimpleResponse;
import com.bancoDev.MicroservicioCuentas.models.TransaccionEntity;
import com.bancoDev.MicroservicioCuentas.models.enums.Operacion;
import com.bancoDev.MicroservicioCuentas.repositories.TransaccionRepository;
import com.bancoDev.MicroservicioCuentas.services.TransaccionService;
import com.bancoDev.MicroservicioCuentas.utils.GeneradorDeCodigoUnico;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {
    
    private final TransaccionRepository _transaccionRepository;

    @Override
    public ApiResponse<List<TransaccionSimpleResponse>> listarTransaccion(String numeroCuenta) {
        List<TransaccionEntity> listadoTransaccion = _transaccionRepository.findAll();
        if(listadoTransaccion.isEmpty()){
            return ApiResponse.<List<TransaccionSimpleResponse>>builder()
            .menssage("Ninguna Transaccion encontrada")
            .data(null)
            .status(false)
            .build();
        }
        List<TransaccionSimpleResponse> listadoMapeado = listadoTransaccion.stream().map(x -> {
            return TransaccionSimpleResponse.builder()
            .numeroTransaccion(x.getNumeroTransaccion())
            .monto(x.getMonto())
            .fecha(x.getFecha())
            .build();
        }).toList();
        return ApiResponse.<List<TransaccionSimpleResponse>>builder()
        .menssage("Transacciones encontradas correctamente")
        .data(listadoMapeado)
        .status(true)
        .build();
    }

    @Override
    public ApiResponse<TransaccionCompletaResponse> detalleTransaccion(String id) {
        TransaccionEntity transaccionEncontrada = _transaccionRepository.findByNumeroTransaccion(id);
        if(transaccionEncontrada == null){
            return ApiResponse.<TransaccionCompletaResponse>builder()
            .menssage("La transaccion no fue encontrada")
            .data(null)
            .status(false)
            .build();
        }
        TransaccionCompletaResponse transaccionMappeado = TransaccionCompletaResponse.builder()
        .numeroTransaccion(transaccionEncontrada.getNumeroTransaccion())
        .monto(transaccionEncontrada.getMonto())
        .fecha(transaccionEncontrada.getFecha())
        .numeroCuentaOrigen(transaccionEncontrada.getNumeroCuentaOrigen())
        .numeroCuentaDestino(transaccionEncontrada.getNumeroCuentaDestino())
        .build();
        return ApiResponse.<TransaccionCompletaResponse>builder()
        .menssage("Transaccion encontrada correctamente")
        .data(transaccionMappeado)
        .status(true)
        .build();
    }

    @Override
    @Transactional
    public boolean guardarTransaccion(TransaccionRequest transaccion, Operacion operacion) {
        try{
            TransaccionEntity transaccionEntity = new TransaccionEntity();
            transaccionEntity.setNumeroTransaccion(GeneradorDeCodigoUnico.generarCodigoUnico());
            transaccionEntity.setMonto(transaccion.getMonto());
            transaccionEntity.setFecha(LocalDateTime.now().toString());
            transaccionEntity.setOperacion(operacion);
            transaccionEntity.setNumeroCuentaOrigen(transaccion.getNumeroCuentaOrigen());
            transaccionEntity.setNumeroCuentaDestino(transaccion.getNumeroCuentaDestino());
            _transaccionRepository.save(transaccionEntity);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }        
    }
  
}
