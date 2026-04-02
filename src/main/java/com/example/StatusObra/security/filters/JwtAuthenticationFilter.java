package com.example.StatusObra.security.filters;


import com.example.StatusObra.persistence.CustomUserDetails;
import com.example.StatusObra.persistence.UsuarioEntity;
import com.example.StatusObra.security.jwt.JwtUtils;
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



    private JwtUtils jwtUtils;
    //Aqui estariamos iyectando la clase JwtUtils porque como no es un component no podemos hacer un autowired
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
            password = userEntity.getPassword();

        }catch (Exception e) {
            throw new RuntimeException(e);
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
        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();
        String token = jwtUtils.generateAccessToken(userDetails.getEmail()); // Usa el email aquí

        response.addHeader("Authorization",  token);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("Message", "Autenticacion correcta");
        httpResponse.put("email", userDetails.getEmail());
        httpResponse.put("username", userDetails.getUsername()); // Agrega el nombre de usuario
        httpResponse.put("roles", userDetails.getAuthorities()); // Agrega los roles
        httpResponse.put("id", userDetails.getId()); // Agrega los id


        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
