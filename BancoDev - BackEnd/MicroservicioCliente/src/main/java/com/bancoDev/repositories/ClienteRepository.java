package com.bancoDev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.bancoDev.models.ClienteEntity;
import java.util.List;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @NativeQuery("select * from tbl_clientes where id = ?1 and activo = true")
    ClienteEntity findByIdActivo(Long id);
    
    ClienteEntity findByDni(String dni);
    List<ClienteEntity> findByActivo(boolean activo);
    ClienteEntity findByCorreoAndContrasenia(String correo, String password);
    ClienteEntity findByCorreoIgnoreCase(String correo);
    ClienteEntity findByDniIgnoreCase(String correo);

}
