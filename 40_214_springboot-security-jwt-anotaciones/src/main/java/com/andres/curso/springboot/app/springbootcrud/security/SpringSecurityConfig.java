package com.andres.curso.springboot.app.springbootcrud.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
                                                           
                                                           /*.requestMatchers(HttpMethod.POST,"/api/users").hasRole("ADMIN")

                                                           .requestMatchers(HttpMethod.GET,"/api/products", "/api/products/{id}").hasAnyRole("ADMIN", "USER")
                                                           .requestMatchers(HttpMethod.POST,"/api/products").hasRole("ADMIN")
                                                           .requestMatchers(HttpMethod.PUT,"/api/products/{id}").hasRole("ADMIN")
                                                           .requestMatchers(HttpMethod.DELETE,"/api/products/{id}").hasRole("ADMIN")*/
                                                           
                                                           .anyRequest().authenticated())
                                                           
                                                           .addFilter(new JwtAuthenticationFilter(this.authenticationManager()))                                //208,se agrega el filtro de authenticacion y por constructor le pasamos el authenticationManager.
                                                           .addFilter(new JwtValidationFilter(this.authenticationManager()))                                    //210, se agrega el filtro de validacion de token.
                                                           
                                                           .csrf(config -> config.disable())
                                                           
                                                           .cors(cors -> cors.configurationSource(corsConfigurationSource())) //215
                                                           
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

    //215, configuracion para poder conectar con un fron como react o angular
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); ///** = la configuracion de cors se va a aplicar a todas las rutas del proyecto.
        return source;
    }

    //215, componente para asignar el corsConfigurationSource(). Es un filtro para q se ejecute en todas las rutas definidas con source.registerCorsConfiguration("/**", config) 
    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter>corsBean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }


}
