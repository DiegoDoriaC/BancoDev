package com.bancoDev.servicesImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.DTOs.request.PagoCrearRequest;
import com.bancoDev.DTOs.request.PrestamoCrearDto;
import com.bancoDev.DTOs.response.PrestamoResponse;
import com.bancoDev.client.interfaces.ClienteClient;
import com.bancoDev.client.interfaces.EmpleadoClient;
import com.bancoDev.client.models.ClienteResponse;
import com.bancoDev.client.models.ClienteSimpleResponse;
import com.bancoDev.client.models.EmpleadoSimpleResponse;
import com.bancoDev.models.PrestamoEntity;
import com.bancoDev.models.enums.Estado;
import com.bancoDev.models.enums.PlazoMeses;
import com.bancoDev.repositories.PrestamoRepository;
import com.bancoDev.services.PagoService;
import com.bancoDev.services.PrestamoService;
import com.bancoDev.utils.mapper.PrestamoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository _prestamoRepository;
    private final PagoService _pagoService;
    private final ClienteClient _clienteClient;
    private final EmpleadoClient _empleadoClient;

    @Override
    public ApiResponse<List<PrestamoResponse>> listarTodosLosPrestamos(Long idCliente) {
        List<PrestamoEntity> listarPrestamos = _prestamoRepository.findByClienteId(idCliente.intValue());
        if(listarPrestamos.isEmpty()){
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Ningun prestamo encontrado")
            .data(null)
            .status(false)
            .build();
        }
        try{
            List<PrestamoResponse> prestamosMappeados = listarPrestamos.stream().map( x -> {
                PrestamoResponse prestamoMappeado = PrestamoMapper.prestamoEntiyToPrestamosResponse(x);
                try {
                    prestamoMappeado.setNombresEmpleado(nombresEmpleado(x.getEmpleadoId()).getData().getNombres());
                    prestamoMappeado.setNombresCliente(nombresCliente(x.getClienteId()).getData().getNombres());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return prestamoMappeado;
            }).toList();
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Prestamos encontrados correctamente")
            .data(prestamosMappeados)
            .status(true)
            .build();
        }
        catch (Exception e){
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Ocurrio un error en el microservicio cliento y/o empleado: " + e.getMessage())
            .data(null)
            .status(false)
            .build();
        }
    }

    @Override
    public ApiResponse<List<PrestamoResponse>> listarPrestamosPagados(Long idCliente) {
        List<PrestamoEntity> listarPrestamos = _prestamoRepository.findByClienteIdAndEstado(idCliente.intValue(), Estado.CANCELADO);
        if(listarPrestamos.isEmpty()){
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Ningun prestamo pagado encontrado")
            .data(null)
            .status(false)
            .build();
        }
        try{
            List<PrestamoResponse> prestamosMappeados = listarPrestamos.stream().map( x -> {
                PrestamoResponse prestamoMappeado = PrestamoMapper.prestamoEntiyToPrestamosResponse(x);
                try {
                    prestamoMappeado.setNombresEmpleado(nombresEmpleado(x.getEmpleadoId()).getData().getNombres());
                    prestamoMappeado.setNombresCliente(nombresCliente(x.getClienteId()).getData().getNombres());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return prestamoMappeado;
            }).toList();
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Prestamos pagados encontrado correctamente")
            .data(prestamosMappeados)
            .status(true)
            .build();
        }
        catch (Exception e){
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Ocurrio un error en el microservicio cliento y/o empleado: " + e.getMessage())
            .data(null)
            .status(false)
            .build();
        }
    }

    @Override
    public ApiResponse<List<PrestamoResponse>> listarPrestamosPendientes(Long idCliente) {
        List<PrestamoEntity> listarPrestamos = _prestamoRepository.findByClienteIdAndEstado(idCliente.intValue(), Estado.PENDIENTE);
        if(listarPrestamos.isEmpty()){
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Ningun prestamo pendiente encontrado")
            .data(null)
            .status(false)
            .build();
        }
        try{
            List<PrestamoResponse> prestamosMappeados = listarPrestamos.stream().map( x -> {
                PrestamoResponse prestamoMappeado = PrestamoMapper.prestamoEntiyToPrestamosResponse(x);
                try {
                    prestamoMappeado.setNombresEmpleado(nombresEmpleado(x.getEmpleadoId()).getData().getNombres());
                    prestamoMappeado.setNombresCliente(nombresCliente(x.getClienteId()).getData().getNombres());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return prestamoMappeado;
            }).toList();
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Prestamos pendientes encontrados correctamente")
            .data(prestamosMappeados)
            .status(true)
            .build();
        }
        catch (Exception e){
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Ocurrio un error inesperado: " + e.getMessage())
            .data(null)
            .status(false)
            .build();
        }
    }

    @Override
    public ApiResponse<List<PrestamoResponse>> listarPrestamosPendientesEmpleado(Long idEmpleado) {
        List<PrestamoEntity> listarPrestamos = _prestamoRepository.findByEmpleadoIdAndEstado(idEmpleado, Estado.PENDIENTE);
        if(listarPrestamos.isEmpty()){
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Ningun prestamo pendiente encontrado")
            .data(null)
            .status(false)
            .build();
        }
        try{
            List<PrestamoResponse> prestamosMappeados = listarPrestamos.stream().map( x -> {
                PrestamoResponse prestamoMappeado = PrestamoMapper.prestamoEntiyToPrestamosResponse(x);
                try {
                    prestamoMappeado.setNombresEmpleado(nombresEmpleado(x.getEmpleadoId()).getData().getNombres());
                    prestamoMappeado.setNombresCliente(nombresCliente(x.getClienteId()).getData().getNombres());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return prestamoMappeado;
            }).toList();
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Prestamos pendientes encontrados correctamente")
            .data(prestamosMappeados)
            .status(true)
            .build();
        }
        catch (Exception e){
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("Ocurrio un error inesperado: " + e.getMessage())
            .data(null)
            .status(false)
            .build();
        }
    }

    @Override
    public ApiResponse<List<PrestamoResponse>> buscarPrestamosPorDniCliente(String dni) {
        ApiResponse<ClienteResponse> clienteEncontrado = _clienteClient.buscarPorDni(dni);
        if(!clienteEncontrado.isStatus()){
            return ApiResponse.<List<PrestamoResponse>>builder()
            .message("El cliente con DNI:" + dni + " no se encuentra registrado")
            .data(null)
            .status(false)
            .build(); 
        }
        return listarTodosLosPrestamos(clienteEncontrado.getData().getId());
    }

    @Override
    public ApiResponse<List<PrestamoResponse>> buscarPrestamosPorDniEmpleado(String dni) {        
        return listarPrestamosPendientesEmpleado(Long.parseLong(dni));
    }
   
    @Override
    public ApiResponse<PrestamoResponse> buscarPrestamo(Long id) {
        PrestamoEntity prestamoOptional = _prestamoRepository.findById(id).orElse(null);
        if(prestamoOptional == null){
            return ApiResponse.<PrestamoResponse>builder()
            .message("El prestamo no fue encontrado")
            .data(null)
            .status(false)
            .build();
        }
        try{
            PrestamoResponse prestamoMappeado = PrestamoMapper.prestamoEntiyToPrestamosResponse(prestamoOptional);
            try {
                prestamoMappeado.setNombresEmpleado(nombresEmpleado(prestamoOptional.getEmpleadoId()).getData().getNombres());
                prestamoMappeado.setNombresCliente(nombresCliente(prestamoOptional.getClienteId()).getData().getNombres());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ApiResponse.<PrestamoResponse>builder()
            .message("Prestamo encontrado correctamente")
            .data(prestamoMappeado)
            .status(true)
            .build();           
        }
        catch (Exception e){
            return ApiResponse.<PrestamoResponse>builder()
            .message("Ocurrio un error en el microservicio cliento y/o empleado: " + e.getMessage())
            .data(null)
            .status(false)
            .build(); 
        }
    }

    @Override
    public ApiResponse<PrestamoResponse> solicitarPrestamo(PrestamoCrearDto prestamo) {

        //Validar el monto maximo al que puede acceder el cliente
        double montoPrestamoMaximo = montoPrestamoMaximo(prestamo.getSocreCrediticio(), prestamo.getPlazoMeses(), prestamo.getSueldoCliente());
        if(prestamo.getMontoSolicitado() > montoPrestamoMaximo){
            return ApiResponse.<PrestamoResponse>builder()
            .message("El credito solicitado excede la cantidad maxima de credito permitida; el monto maximo para un prestamo en " + prestamo.getPlazoMeses() + " coutas seria: S/" + montoPrestamoMaximo)
            .data(null)
            .status(false)
            .build(); 
        }

        try {
            //Crear y guardar el prestamo
            PrestamoEntity prestamoEntity = new PrestamoEntity();
            prestamoEntity.setMonto(new BigDecimal(prestamo.getMontoSolicitado()));
            prestamoEntity.setPlazoMeses(prestamo.getPlazoMeses());
            prestamoEntity.setEstado(Estado.PENDIENTE);
            prestamoEntity.setEmpleadoId(prestamo.getEmpleadoId());
            prestamoEntity.setClienteId(prestamo.getClienteId());
            prestamoEntity.setFechaAprobacion(LocalDateTime.now());
            prestamoEntity.setTazaInteres(8);
            PrestamoEntity prestamoGuardado = _prestamoRepository.save(prestamoEntity) ;           
            
            //Si todo esta bien retornar la respuesta exitosa
            return ApiResponse.<PrestamoResponse>builder()
            .message("El prestamo fué solicitado correctamente, todavía esta pendiente de aprobación")
            .data(PrestamoMapper.prestamoEntiyToPrestamosResponse(prestamoGuardado))
            .status(true)
            .build();

        } catch (Exception e) {
            return ApiResponse.<PrestamoResponse>builder()
            .message("Ocurrió un error al solicitar el prestamo: " + e.getMessage()) 
            .data(null)
            .status(false)
            .build();
        }

    }

    @Override
    public ApiResponse<PrestamoResponse> crearPrestamo(Long idPrestamo){
        Optional<PrestamoEntity> prestamo = _prestamoRepository.findById(idPrestamo);
        if(prestamo.isPresent()){
            PrestamoEntity prestamoOptenido = prestamo.get();
            prestamoOptenido.setEstado(Estado.APROBADO);
            try{             
                PrestamoEntity prestamoGuardado = _prestamoRepository.save(prestamoOptenido);
                //Crear y guardar las cuotas
                PagoCrearRequest crearCoutas = PagoCrearRequest.builder()
                .prestamoId(prestamoGuardado.getId().intValue())
                .numeroCoutas(prestamoOptenido.getPlazoMeses().getValor())
                .montoPago(calcularMontoPago( prestamoOptenido.getMonto().doubleValue(), prestamoOptenido.getPlazoMeses(), 8))
                .build();
                
                //Comprobar si hubo algun error al crear las coutas
                var pagoCreado = _pagoService.crearPago(crearCoutas);
                if(pagoCreado.isStatus() == false){
                    throw new Exception(pagoCreado.getMessage());
                }                    
                //Si todo esta bien retornar la respuesta exitosa
                return ApiResponse.<PrestamoResponse>builder()
                .message("Prestamo aprobado correctamente")
                .data(null)
                .status(true)
                .build();
            }
            catch(Exception e){
                return ApiResponse.<PrestamoResponse>builder()
                .message("Ocurrio un error: " + e.getMessage())
                .data(null)
                .status(false)
                .build();
            }
        } 
        else{
            return ApiResponse.<PrestamoResponse>builder()
            .message("El prestamo no fue encontrado")
            .data(null)
            .status(false)
            .build();
        }
    }

    
    //METODOS PRIVADOS PARA COMPLEMENTAR LA LOGICA DE NEGOCIO

    private double montoPrestamoMaximo(int scoreCrediticio, PlazoMeses plazoMeses, BigDecimal sueldo){
        double montoDePrestamoSegunScoreCrediticio = 0;
        switch (scoreCrediticio) {
            case 5: montoDePrestamoSegunScoreCrediticio = 0.40; break;
            case 4: montoDePrestamoSegunScoreCrediticio = 0.35; break;
            case 3: montoDePrestamoSegunScoreCrediticio = 0.30; break;
            case 2: montoDePrestamoSegunScoreCrediticio = 0.25; break;      
            default: montoDePrestamoSegunScoreCrediticio = 0.20; break;
        }
        double cantidadPrestamoMaximo = (sueldo.doubleValue() * montoDePrestamoSegunScoreCrediticio) * plazoMeses.getValor();
        return cantidadPrestamoMaximo;
    }

    private BigDecimal calcularMontoPago(double montoSolicitado, PlazoMeses plazoMeses, int tazaInteres){
        double interes = tazaInteres * 0.01;
        BigDecimal montoCalculado = new BigDecimal((montoSolicitado / plazoMeses.getValor()) * (1 + interes));
        return montoCalculado.setScale(3, RoundingMode.HALF_UP);
    }

    private ApiResponse<ClienteSimpleResponse> nombresCliente(int id) throws Exception{
        ApiResponse<ClienteSimpleResponse> respuesta = _clienteClient.mostrarNombreClientePorId(Long.valueOf(id));
        if(!respuesta.isStatus()) throw new Exception(respuesta.getMessage());
        return ApiResponse.<ClienteSimpleResponse>builder()
            .message(respuesta.getMessage())
            .data(respuesta.getData())
            .status(true)
            .build();
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