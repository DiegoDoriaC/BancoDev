package com.bancoDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancoDev.models.PagoEntity;
import com.bancoDev.models.enums.EstadoPago;

public interface PagoRepository extends JpaRepository<PagoEntity, Long> {

    List<PagoEntity> findByEstadoAndPrestamoId_Id(EstadoPago estado, Long id);
    List<PagoEntity> findByPrestamoId_Id(Long id);


}
