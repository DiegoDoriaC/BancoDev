package com.bancoDev.MicroservicioCuentas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bancoDev.MicroservicioCuentas.models.TransaccionEntity;


public interface TransaccionRepository extends MongoRepository<TransaccionEntity, String> {

    TransaccionEntity findByNumeroTransaccion (String numeroTransaccion);

}
