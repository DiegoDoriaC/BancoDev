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
import com.bancoDev.DTOs.response.PagoResponse;
import com.bancoDev.models.PagoEntity;
import com.bancoDev.models.PrestamoEntity;
import com.bancoDev.models.enums.EstadoPago;
import com.bancoDev.repositories.PagoRepository;
import com.bancoDev.services.PagoService;
import com.bancoDev.utils.mapper.PagoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository _pagoRepository;

    @Override
    public ApiResponse<List<PagoResponse>> listarTodosLosPagos(Long idPrestamo) {
        List<PagoEntity> listadoDePagos = _pagoRepository.findAll();
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
        List<PagoEntity> pagosPagados = _pagoRepository.findByEstadoAndPrestamoId_Id("PAGADO", idPrestamo);
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
        List<PagoEntity> pagosPendientes = _pagoRepository.findByEstadoAndPrestamoId_Id("PENDIENTE", idPrestamo);
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
        pagoResponse.setNombreEmpleado("Falta implementar nombres");
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
        pagoMappeado.setTipoPago(pagoRealizar.getTipoPago());
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
    public ApiResponse<List<PagoResponse>> crearPago(PagoCrearRequest pagoCrear) {
        try{
            List<PagoResponse> listadoPrestamos = new ArrayList<>();
            PrestamoEntity prestamoEntity = new PrestamoEntity();
            prestamoEntity.setId(Long.valueOf(pagoCrear.getPrestamoId()));
            for(int i = 0; i < pagoCrear.getNumeroCoutas(); i++) {
                PagoEntity pagoEntity = PagoEntity.builder()
                .fechaPagoEstimada(LocalDate.now().plusMonths(i))
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

}