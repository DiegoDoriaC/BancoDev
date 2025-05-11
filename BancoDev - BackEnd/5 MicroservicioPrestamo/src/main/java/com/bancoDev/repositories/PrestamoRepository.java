package com.bancoDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancoDev.models.PrestamoEntity;
import com.bancoDev.models.enums.Estado;

public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Long> {

    List<PrestamoEntity> findByClienteId(int clienteId);
    List<PrestamoEntity> findByClienteIdAndEstado(int clienteId, Estado estado);
    List<PrestamoEntity> findByEmpleadoIdAndEstado(Long empleadoId, Estado estado);

}
