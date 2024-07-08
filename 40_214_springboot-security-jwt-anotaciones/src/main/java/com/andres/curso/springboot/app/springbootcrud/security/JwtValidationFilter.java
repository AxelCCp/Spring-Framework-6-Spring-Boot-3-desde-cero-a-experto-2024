package com.andres.curso.springboot.app.springbootcrud.security;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//209,210 - filtro de validacion de token
public class JwtValidationFilter extends BasicAuthenticationFilter{

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION);
        if(header == null || !header.startsWith(TokenJwtConfig.PREFIX_TOKEN)){
            chain.doFilter(request, response);                                                                                      //falla, pero de igual manera se continua con la cadena de filtro y se pasa el request y el response.
            return;
        }
        String token = header.replace(TokenJwtConfig.PREFIX_TOKEN, "");
        try {
            Claims claims = Jwts.parser().verifyWith(TokenJwtConfig.SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();  //forma 1
            //String username2 = (String) claims.get("username");   //forma 2
            Object authoritiesClaims = claims.get("authorities");
            
            //se procesan los roles
            Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
                                                                .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)            //acoplamiento de constructores.  lee nota en SimpleGrantedAuthorityJsonCreator.        
                                                                .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));
            
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);    //se hace la authenticacion.
            chain.doFilter(request, response);  //se continua con la cadena de filtro y se pasa el request y el response.
        } catch (JwtException e) {
            Map<String, String>body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "EL token jwt es invalido!");
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(TokenJwtConfig.CONTENT_TYPE);
        }       
        
    }

    

}
