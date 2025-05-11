package com.bancoDev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancoDev.models.HistorialEntity;

@Repository
public interface HistorialRepositoy extends JpaRepository<HistorialEntity, Long> {

    HistorialEntity findByIdCliente_Id(Long id);

}
