package com.andres.curso.springboot.app.springbootcrud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.andres.curso.springboot.app.springbootcrud.security.filter.JwtAuthenticationFilter;


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
                                                           .requestMatchers(HttpMethod.POST,"/api/users").hasRole("ADMIN")

                                                           .requestMatchers(HttpMethod.GET,"/api/products", "/api/products/{id}").hasAnyRole("ADMIN", "USER")
                                                           .requestMatchers(HttpMethod.POST,"/api/products").hasRole("ADMIN")
                                                           .requestMatchers(HttpMethod.PUT,"/api/products/{id}").hasRole("ADMIN")
                                                           .requestMatchers(HttpMethod.DELETE,"/api/products/{id}").hasRole("ADMIN")
                                                           
                                                           .anyRequest().authenticated())
                                                           
                                                           .addFilter(new JwtAuthenticationFilter(this.authenticationManager()))                                //208,se agrega el filtro de authenticacion y por constructor le pasamos el authenticationManager.
                                                           .addFilter(new JwtValidationFilter(this.authenticationManager()))                                    //210, se agrega el filtro de validacion de token.
                                                           
                                                           .csrf(config -> config.disable())
                                                           .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  //198,esto quiere decir que la session es sin estado, ya q la session se va a manejar a través del token. 
                                                           .build();     
    }


    //208,se configura el authenticationManager. este debe estar configurado aquí y en la clase filtro JwtAuthenticationFilter. Este debe ser el mismo authenticationManager para las 2 clases.
    
    @Autowired  //208
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean   //208
    AuthenticationManager authenticationManager() throws Exception {
        return this.authenticationConfiguration.getAuthenticationManager();
    } 
}
