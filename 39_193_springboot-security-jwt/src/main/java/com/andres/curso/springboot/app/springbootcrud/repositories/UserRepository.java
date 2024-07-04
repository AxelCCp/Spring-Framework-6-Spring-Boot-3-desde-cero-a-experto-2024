package com.andres.curso.springboot.app.springbootcrud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.andres.curso.springboot.app.springbootcrud.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

    boolean existByUsername(String username);

    Optional<User>findByUsername(String username);
}
