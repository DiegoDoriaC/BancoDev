package com.bancoDev.servicesImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.request.PagoCrearRequest;
import com.bancoDev.DTOs.request.PagoRealizarRequest;
import com.bancoDev.DTOs.request.RealizarPagoTransaccionRequest;
import com.bancoDev.DTOs.response.PagoResponse;
import com.bancoDev.client.interfaces.CuentaClient;
import com.bancoDev.client.interfaces.EmpleadoClient;
import com.bancoDev.client.models.CuentaResponse;
import com.bancoDev.client.models.EmpleadoSimpleResponse;
import com.bancoDev.models.PagoEntity;
import com.bancoDev.models.PrestamoEntity;
import com.bancoDev.models.enums.EstadoPago;
import com.bancoDev.models.enums.TipoPago;
import com.bancoDev.repositories.PagoRepository;
import com.bancoDev.services.PagoService;
import com.bancoDev.utils.mapper.PagoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository _pagoRepository;
    private final CuentaClient _cuentaClient;
    private final EmpleadoClient _empleadoClient;

    @Override
    public ApiResponse<List<PagoResponse>> listarTodosLosPagos(Long idPrestamo) {
        List<PagoEntity> listadoDePagos = _pagoRepository.findByPrestamoId_Id(idPrestamo);
        if(listadoDePagos.isEmpty()){
            return ApiResponse.<List<PagoResponse>>builder()
            .message("Ningun pago encontrado")
            .data(null)
            .status(false)
            .build();
        }
        List<PagoResponse> listadoPagosMappeado = new ArrayList<>();
        for (PagoEntity item : listadoDePagos) {
            PagoResponse pagoMappeado = PagoMapper.pagoEntityToPagoResponse(item);
            pagoMappeado.setNombreEmpleado("Todavia falta implementar");
            listadoPagosMappeado.add(pagoMappeado);
        }
        return ApiResponse.<List<PagoResponse>>builder()
        .message("Pagos encontrados correctamente")
        .data(listadoPagosMappeado)
        .status(true)
        .build();
    }

    @Override
    public ApiResponse<List<PagoResponse>> listarPagosPagados(Long idPrestamo) {
        List<PagoEntity> pagosPagados = _pagoRepository.findByEstadoAndPrestamoId_Id(EstadoPago.PAGADO, idPrestamo);
        if(pagosPagados.isEmpty()){
            return ApiResponse.<List<PagoResponse>>builder()
            .message("Ninguna cuota pagada encontrada")
            .data(null)
            .status(false)
            .build();
        }
        List<PagoResponse> listadoPagosPagadosMappeado = new ArrayList<>();
        for (PagoEntity item : pagosPagados) {
            PagoResponse pagoMappeado = PagoMapper.pagoEntityToPagoResponse(item);
            pagoMappeado.setNombreEmpleado("Todavia falta implementar");
            listadoPagosPagadosMappeado.add(pagoMappeado);
        }
        return ApiResponse.<List<PagoResponse>>builder()
        .message("Coutas pagadas encontradas correctamente")
        .data(listadoPagosPagadosMappeado)
        .status(true)
        .build();
    }

    @Override
    public ApiResponse<List<PagoResponse>> listarPagosPendiendes(Long idPrestamo) {
        List<PagoEntity> pagosPendientes = _pagoRepository.findByEstadoAndPrestamoId_Id(EstadoPago.PENDIENTE, idPrestamo);
        if(pagosPendientes.isEmpty()){
            return ApiResponse.<List<PagoResponse>>builder()
            .message("Ninguna cuota pendiente encontrada")
            .data(null)
            .status(false)
            .build();
        }
        List<PagoResponse> listadoPagosPagadosMappeado = new ArrayList<>();
        for (PagoEntity item : pagosPendientes) {
            PagoResponse pagoMappeado = PagoMapper.pagoEntityToPagoResponse(item);
            pagoMappeado.setNombreEmpleado("Todavia falta implementar");
            listadoPagosPagadosMappeado.add(pagoMappeado);
        }
        return ApiResponse.<List<PagoResponse>>builder()
        .message("Coutas pendiente encontradas correctamente")
        .data(listadoPagosPagadosMappeado)
        .status(true)
        .build();
    }

    @Override
    public ApiResponse<PagoResponse> buscarPago(Long id) {
        Optional<PagoEntity> pagoOptional = _pagoRepository.findById(id);
        if(!pagoOptional.isPresent()){
            return ApiResponse.<PagoResponse>builder()
            .message("Pago no encontrado")
            .data(null)
            .status(false)
            .build();
        }
        PagoResponse pagoResponse = PagoMapper.pagoEntityToPagoResponse(pagoOptional.get());
        pagoResponse.setNombreEmpleado("");
        if(pagoOptional.get().getEstado().name().equals("PAGADO") || pagoOptional.get().getEstado().name().equals("ATRASADO"))
            try {
                pagoResponse.setNombreEmpleado(nombresEmpleado(pagoOptional.get().getEmpleadoId()).getData().getNombres());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        return ApiResponse.<PagoResponse>builder()
        .message("Pago encontrado correctamente")
        .data(pagoResponse)
        .status(true)
        .build();        
    }

    @Override
    @Transactional
    public ApiResponse<PagoResponse> realizarPago(PagoRealizarRequest pagoRealizar) {
        Optional<PagoEntity> pagoOptional = _pagoRepository.findById(Long.valueOf(pagoRealizar.getPagoId()));
        if(!pagoOptional.isPresent()){
            return ApiResponse.<PagoResponse>builder()
            .message("El pago a realizar no fue encontrado")
            .data(null)
            .status(false)
            .build();
        }
        PagoEntity pagoMappeado = pagoOptional.get();
        pagoMappeado.setFechaPagoRealizada(LocalDateTime.now());
        pagoMappeado.setMontoPago(pagoRealizar.getMontoPagado());
        pagoMappeado.setEstado(EstadoPago.PAGADO);        
        if(pagoMappeado.getFechaPagoEstimada().isBefore(LocalDate.now())) pagoMappeado.setEstado(EstadoPago.ATRASADO);
        pagoMappeado.setTipoPago(TipoPago.VENTANILLA);
        pagoMappeado.setEmpleadoId(pagoRealizar.getEmpleadoId());
        try{
            PagoEntity pagoRealizado = _pagoRepository.save(pagoMappeado);
            return ApiResponse.<PagoResponse>builder()
            .message("Pago realizado correctamente")
            .data(PagoMapper.pagoEntityToPagoResponse(pagoRealizado))
            .status(true)
            .build();
        }
        catch(Exception e){
            return ApiResponse.<PagoResponse>builder()
            .message("No se pudo realizar el pago: " + e.getMessage())
            .data(null)
            .status(false)
            .build();
        }
    }

    @Override
    @Transactional
    public ApiResponse<PagoResponse> realizarPagoTransaccion(RealizarPagoTransaccionRequest pagoRealizar) {
        Optional<PagoEntity> pagoOptional = _pagoRepository.findById(Long.valueOf(pagoRealizar.getPagoId()));
        if(!pagoOptional.isPresent()){
            return ApiResponse.<PagoResponse>builder()
            .message("El pago a realizar no fue encontrado")
            .data(null)
            .status(false)
            .build();
        }
        PagoEntity pagoMappeado = pagoOptional.get();
        pagoMappeado.setFechaPagoRealizada(LocalDateTime.now());
        pagoMappeado.setMontoPago(pagoRealizar.getMontoPagado());
        pagoMappeado.setEstado(EstadoPago.PAGADO);        
        if(pagoMappeado.getFechaPagoEstimada().isBefore(LocalDate.now())) pagoMappeado.setEstado(EstadoPago.ATRASADO);
        pagoMappeado.setTipoPago(TipoPago.TRANSFERENCIA);
        pagoMappeado.setEmpleadoId(0);
        try{
            // Guardar el pago            
            PagoEntity pagoRealizado = _pagoRepository.save(pagoMappeado);
            // Disminuir el dinero de la cuenta quien realizo el pago
            ApiResponse<CuentaResponse> respuestaPago = _cuentaClient.disminuirCuenta(String.valueOf(pagoRealizado.getPrestamoId().getClienteId()), pagoRealizado.getMontoPago());
            if(!respuestaPago.isStatus()){
                return ApiResponse.<PagoResponse>builder()
                .message(respuestaPago.getMessage())
                .data(null)
                .status(false)
                .build();
            }
            return ApiResponse.<PagoResponse>builder()
            .message("Pago realizado correctamente")
            .data(PagoMapper.pagoEntityToPagoResponse(pagoRealizado))
            .status(true)
            .build();
        }
        catch(Exception e){
            return ApiResponse.<PagoResponse>builder()
            .message("No se pudo realizar el pago: " + e.getMessage())
            .data(null)
            .status(false)
            .build();
        }
    }

    @Override
    public ApiResponse<List<PagoResponse>> crearPago(PagoCrearRequest pagoCrear) {
        try{
            List<PagoResponse> listadoPrestamos = new ArrayList<>();
            PrestamoEntity prestamoEntity = new PrestamoEntity();
            prestamoEntity.setId(Long.valueOf(pagoCrear.getPrestamoId()));
            for(int i = 0; i < pagoCrear.getNumeroCoutas(); i++) {
                PagoEntity pagoEntity = PagoEntity.builder()
                .fechaPagoEstimada(LocalDate.now().plusMonths(i+1))
                .fechaPagoRealizada(null)
                .montoPago(pagoCrear.getMontoPago())
                .estado(EstadoPago.PENDIENTE)
                .tipoPago(null)
                .empleadoId(0)
                .prestamoId(prestamoEntity)
                .build();
                listadoPrestamos.add(PagoMapper.pagoEntityToPagoResponse(_pagoRepository.save(pagoEntity)));
            }
            return ApiResponse.<List<PagoResponse>>builder()
            .message("Coutas creadas correctamente")
            .data(listadoPrestamos)
            .status(true)
            .build();
        }
        catch (Exception e){
            return ApiResponse.<List<PagoResponse>>builder()
            .message("No se pudo registrar las cuotas: " + e.getMessage())
            .data(null)
            .status(false)
            .build();
        }
    }

    private ApiResponse<EmpleadoSimpleResponse> nombresEmpleado(int id) throws Exception{
        ApiResponse<EmpleadoSimpleResponse> respuesta = _empleadoClient.mostrarNombreEmpleadoPorId(Long.valueOf(id));
        if(!respuesta.isStatus()) throw new Exception(respuesta.getMessage());
        return ApiResponse.<EmpleadoSimpleResponse>builder()
            .message(respuesta.getMessage())
            .data(respuesta.getData())
            .status(true)
            .build();
    }

}