package com.bancoDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancoDev.models.PrestamoEntity;

public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Long> {

    List<PrestamoEntity> findByClienteId(int clienteId);
    List<PrestamoEntity> findByClienteIdAndEstadoIgnoreCase(int clienteId, String estado);

}
