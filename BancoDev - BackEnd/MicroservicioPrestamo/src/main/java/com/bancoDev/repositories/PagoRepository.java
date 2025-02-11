package com.bancoDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancoDev.models.PagoEntity;


public interface PagoRepository extends JpaRepository<PagoEntity, Long> {

    List<PagoEntity> findByEstadoAndPrestamoId_Id(String estado, Long id);
    List<PagoEntity> findByPrestamoId_Id(Long id);


}
