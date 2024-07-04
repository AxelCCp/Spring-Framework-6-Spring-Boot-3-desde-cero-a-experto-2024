package com.andres.curso.springboot.app.springbootcrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SpringSecurityConfig {

    //genera un componente de spring que devuelve una instancia de BCryptPasswordEncoder.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //198,devuelve un SecurityFilterChain, es decir un filtro q valida los request. autoriza, da permisos o nuega permisos.
    //se inyecta de forma automatica un obj q se llama httpSecurity, para dar seguridad a las peticiones http.
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz.requestMatchers(HttpMethod.GET,"/api/users").permitAll()
                                                           .requestMatchers(HttpMethod.POST,"/api/users/register").permitAll()
                                                           .anyRequest().authenticated())
                                                           .csrf(config -> config.disable())
                                                           .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //198,esto quiere decir que la session es sin estado, ya q la session se va a manejar a través del token. 
                                                           .build();     
    }

}
