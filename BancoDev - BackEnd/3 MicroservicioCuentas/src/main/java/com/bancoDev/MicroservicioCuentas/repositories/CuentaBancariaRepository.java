package com.bancoDev.MicroservicioCuentas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bancoDev.MicroservicioCuentas.models.CuentaBancariaEntity;


public interface CuentaBancariaRepository extends MongoRepository<CuentaBancariaEntity, String> {

    CuentaBancariaEntity findByClienteId(int clienteId);

}
