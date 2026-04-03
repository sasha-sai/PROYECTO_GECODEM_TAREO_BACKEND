package com.gecodem.tareo.security.filters;


import com.gecodem.tareo.model.UsuarioLogueado;
import com.gecodem.tareo.persistence.UsuarioEntity;
import com.gecodem.tareo.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        UsuarioEntity userEntity = null;
        String email ="";
        String password ="";

        try {
            userEntity = new ObjectMapper().readValue(request.getInputStream(), UsuarioEntity.class);
            email = userEntity.getEmail();
            password = userEntity.getContrasena();

        }catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(email, password);

        return getAuthenticationManager().authenticate(authenticationToken) ;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //obtenemos los datos del usuario que se a logueado correctamente
        UsuarioLogueado userDetails = (UsuarioLogueado) authResult.getPrincipal();
        String token = jwtUtils.generateAccessToken(userDetails.getEmail()); // Usa el email aquí

        response.addHeader("Authorization",  token);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("Message", "Autenticacion correcta");
        httpResponse.put("email", userDetails.getEmail());
        httpResponse.put("username", userDetails.getUsername());
        httpResponse.put("rol", userDetails.getRol());
        httpResponse.put("id", userDetails.getId());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
