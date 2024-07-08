package com.andres.curso.springboot.app.springbootcrud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
//189
@Constraint(validatedBy = RequiredValidation.class)
@Retention(RetentionPolicy.RUNTIME)                                 //se ejecuta en tiempo de ejecucion
@Target({ ElementType.FIELD, ElementType.METHOD })                  //se puede ejecutar sobre un atributo o un metodo.
public @interface IsRequired {
    String message() default "es requerido usando anotaciones";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
