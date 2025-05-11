package com.bancoDev.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancoDev.DTOs.Roles;
import com.bancoDev.Models.EmpleadoEntity;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {

    List<EmpleadoEntity> findByIdSucursal_IdAndRol(Long id, Roles roles);
    EmpleadoEntity findByCorreoAndPassword(String correo, String passsword);

}
