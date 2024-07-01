package com.andres.curso.springboot.jpa.springbootjparelationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.andres.curso.springboot.jpa.springbootjparelationship.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{

    //para que vaya a buscar al cliente en una sola consulta, incluyendo sus direcciones.
    @Query("select c from Client c join fetch c.addresses")
    Optional<Client> findOne(Long id);
}
