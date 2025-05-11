package com.bancoDev.MicroservicioCuentas.services;

import java.util.List;

import com.bancoDev.MicroservicioCuentas.DTOs.ApiResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.request.TransaccionRequest;
import com.bancoDev.MicroservicioCuentas.DTOs.response.TransaccionCompletaResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.response.TransaccionSimpleResponse;
import com.bancoDev.MicroservicioCuentas.models.enums.Operacion;

public interface TransaccionService {

    ApiResponse<List<TransaccionSimpleResponse>> listarTransaccion(String numeroCuenta);
    ApiResponse<TransaccionCompletaResponse> detalleTransaccion(String id);    
    boolean guardarTransaccion(TransaccionRequest transaccion, Operacion operacion);

}
