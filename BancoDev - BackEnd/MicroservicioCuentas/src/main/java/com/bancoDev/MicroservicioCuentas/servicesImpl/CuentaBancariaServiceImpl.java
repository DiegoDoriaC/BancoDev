package com.bancoDev.MicroservicioCuentas.servicesImpl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bancoDev.MicroservicioCuentas.DTOs.ApiResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.request.TransaccionRequest;
import com.bancoDev.MicroservicioCuentas.DTOs.request.TransferirDineroRequest;
import com.bancoDev.MicroservicioCuentas.DTOs.response.CuentaResponse;
import com.bancoDev.MicroservicioCuentas.DTOs.response.TransferenciaResponse;
import com.bancoDev.MicroservicioCuentas.models.CuentaBancariaEntity;
import com.bancoDev.MicroservicioCuentas.models.enums.Operacion;
import com.bancoDev.MicroservicioCuentas.repositories.CuentaBancariaRepository;
import com.bancoDev.MicroservicioCuentas.services.CuentaBancariaService;
import com.bancoDev.MicroservicioCuentas.utils.GeneradorDeCodigoUnico;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    private final CuentaBancariaRepository _cuentaRepository;
    private final TransaccionServiceImpl _transaccionRepository;

    @Override
    public ApiResponse<CuentaResponse> verEstadoCuenta(int idCliente) {
        CuentaBancariaEntity cuentaEncontrada = _cuentaRepository.findByClienteId(idCliente);
        if(cuentaEncontrada == null){
            return ApiResponse.<CuentaResponse>builder()
            .menssage("La cuenta bancaria no fue encontrada")
            .data(null)
            .status(false)
            .build();
        }
        CuentaResponse cuentaMappeada = CuentaResponse.builder()
        .numeroCuenta(cuentaEncontrada.getNumeroCuenta())
        .montoDinero(cuentaEncontrada.getMontoDinero())
        .build();
        return ApiResponse.<CuentaResponse>builder()
        .menssage("Cuenta bancaria encontrada correctamente")
        .data(cuentaMappeada)
        .status(true)
        .build();
    }

    @Override
    @Transactional
    public ApiResponse<CuentaResponse> recargarCuenta(String idCuenta, BigDecimal montoDinero) {
        CuentaBancariaEntity cuentaOptional = _cuentaRepository.findById(idCuenta).orElse(null);
        if(cuentaOptional == null) {
            return ApiResponse.<CuentaResponse>builder()
            .menssage("No se pudo encontrar la cuenta a recargar dinero")
            .data(null)
            .status(false)
            .build();
        }
        try{
            cuentaOptional.setMontoDinero(cuentaOptional.getMontoDinero().add(montoDinero));
            CuentaBancariaEntity cuentaActualizada = _cuentaRepository.save(cuentaOptional);
            CuentaResponse cuentaMappeada = CuentaResponse.builder()
            .numeroCuenta(cuentaActualizada.getNumeroCuenta())
            .montoDinero(cuentaActualizada.getMontoDinero())
            .build();
            return ApiResponse.<CuentaResponse>builder()
            .menssage("Dinero agregado correctamente")
            .data(cuentaMappeada)
            .status(true)
            .build();
        }
        catch(Exception e){
            return ApiResponse.<CuentaResponse>builder()
            .menssage("No se pudo agregar el dinero, error: " + e.getMessage())
            .data(null)
            .status(false)
            .build();
        }
    }
    
    @Override
    @Transactional
    public ApiResponse<CuentaResponse> disminuirCuenta(String idCuenta, BigDecimal montoDinero) {
        CuentaBancariaEntity cuentaOptional = _cuentaRepository.findById(idCuenta).orElse(null);
        if(cuentaOptional == null) {
            return ApiResponse.<CuentaResponse>builder()
            .menssage("No se pudo encontrar la cuenta a descontar el dinero")
            .data(null)
            .status(false)
            .build();
        }
        try{
            if(cuentaOptional.getMontoDinero().compareTo(montoDinero) == -1){
                return ApiResponse.<CuentaResponse>builder()
                .menssage("No cuenta con suficiente dinero para realizar el pago")
                .data(null)
                .status(false)
                .build();
            }
            cuentaOptional.setMontoDinero(cuentaOptional.getMontoDinero().subtract(montoDinero));
            CuentaBancariaEntity cuentaActualizada = _cuentaRepository.save(cuentaOptional);
            CuentaResponse cuentaMappeada = CuentaResponse.builder()
            .numeroCuenta(cuentaActualizada.getNumeroCuenta())
            .montoDinero(cuentaActualizada.getMontoDinero())
            .build();
            return ApiResponse.<CuentaResponse>builder()
            .menssage("Dinero descontado correctamente")
            .data(cuentaMappeada)
            .status(true)
            .build();
        }
        catch(Exception e){
            return ApiResponse.<CuentaResponse>builder()
            .menssage("No se pudo descontar el dinero, error: " + e.getMessage())
            .data(null)
            .status(false)
            .build();
        }
    }

    @Override
    @Transactional
    public ApiResponse<CuentaResponse> crearCuenta(int idCliente) {
        try{
            CuentaBancariaEntity cuentaEntity = new CuentaBancariaEntity();
            cuentaEntity.setNumeroCuenta(GeneradorDeCodigoUnico.generarCodigoUnico());
            cuentaEntity.setMontoDinero(new BigDecimal(0));
            cuentaEntity.setClienteId(idCliente);
            CuentaBancariaEntity cuentaCreada = _cuentaRepository.save(cuentaEntity);
            CuentaResponse cuentaMappeada = CuentaResponse.builder()
            .numeroCuenta(cuentaCreada.getNumeroCuenta())
            .montoDinero(cuentaCreada.getMontoDinero())
            .build();
            return ApiResponse.<CuentaResponse>builder()
            .menssage("Cuenta creada correctamente")
            .data(cuentaMappeada)
            .status(true)
            .build(); 
        }
        catch(Exception e){
            return ApiResponse.<CuentaResponse>builder()
            .menssage("No se pudo crear la cuenta, error: " + e.getMessage())
            .data(null)
            .status(false)
            .build(); 
        }

    }

    @Override
    @Transactional
    public ApiResponse<TransferenciaResponse> transferirDinero(TransferirDineroRequest transferir) {
        try{
            ApiResponse<CuentaResponse> cuentaCliente = verEstadoCuenta(transferir.getClienteId());
            var disminuirCuentaResponse = disminuirCuenta(cuentaCliente.getData().getNumeroCuenta(), transferir.getMonto());
            if(disminuirCuentaResponse.isStatus() == false){
                return ApiResponse.<TransferenciaResponse>builder()
                .menssage(disminuirCuentaResponse.getMenssage())
                .data(null)
                .status(false)
                .build();
            }
            var recargarCuentaResponse = recargarCuenta(transferir.getCuentaDestino(), transferir.getMonto());
            if(recargarCuentaResponse.isStatus() == false){
                return ApiResponse.<TransferenciaResponse>builder()
                .menssage(recargarCuentaResponse.getMenssage())
                .data(null)
                .status(false)
                .build();
            }
            TransaccionRequest transaccionRequest = TransaccionRequest.builder()
            .numeroCuentaOrigen(cuentaCliente.getData().getNumeroCuenta())
            .numeroCuentaDestino(transferir.getCuentaDestino())
            .monto(transferir.getMonto())
            .build();
            TransferenciaResponse transferenciaMappeado = TransferenciaResponse.builder()
            .numeroCuentaOrigen(cuentaCliente.getData().getNumeroCuenta())
            .numeroCuentaDestino(transferir.getCuentaDestino())
            .montoDinero(transferir.getMonto())
            .build();
            boolean respuestaTransaccion = _transaccionRepository.guardarTransaccion(transaccionRequest, Operacion.TRANSACCION);
            if(respuestaTransaccion == false){
                return ApiResponse.<TransferenciaResponse>builder()
                .menssage("No se pudo transferir el dinero")
                .data(null)
                .status(false)
                .build();
            }
            return ApiResponse.<TransferenciaResponse>builder()
            .menssage("Dinero transferido correctamente")
            .data(transferenciaMappeado)
            .status(true)
            .build();
        }
        catch(Exception e){
            return ApiResponse.<TransferenciaResponse>builder()
                .menssage("No se pudo transferir el dinero, error: " + e.getMessage())
                .data(null)
                .status(false)
                .build();
        }

    }

    // @Override
    // public boolean pagarCouta(PagarCuentaRequest pagar) {
    //     PagoRealizarRequest pagoRealizar = new PagoRealizarRequest();
    // }

}
