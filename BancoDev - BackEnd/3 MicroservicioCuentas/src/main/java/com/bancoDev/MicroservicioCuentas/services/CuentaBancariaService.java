package com.bancoDev.MicroservicioCuentas.services;

import com.bancoDev.MicroservicioCuentas.DTOs.request.TransferirDineroRequest;
import com.bancoDev.MicroservicioCuentas.DTOs.response.CuentaResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.response.TransferenciaResponse;

import java.math.BigDecimal;

import com.bancoDev.MicroservicioCuentas.DTOs.ApiResponse;

public interface CuentaBancariaService {

    ApiResponse<CuentaResponse> verEstadoCuenta(int idCliente);
    ApiResponse<TransferenciaResponse> transferirDinero(TransferirDineroRequest transferir);
    ApiResponse<CuentaResponse> crearCuenta(int idCliente);
    
    ApiResponse<CuentaResponse> recargarCuenta(String idCuenta, BigDecimal montoDinero);
    ApiResponse<CuentaResponse> disminuirCuenta(String idCuenta, BigDecimal montoDinero);
    //boolean pagarCouta(PagarCuentaRequest pagar);

}
