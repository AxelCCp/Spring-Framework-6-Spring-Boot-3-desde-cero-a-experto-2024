package com.andres.curso.springboot.app.springbootcrud.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//210
//se usa para adaptar el constructor de SimpleGrantedAuthority a nuestro código. ya que en la cabecera se pasan los roles como authorities y deben pasarse a SimpleGrantedAuthority con el nombre de roles.
public abstract class SimpleGrantedAuthorityJsonCreator {

    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){
        
    }

}
