package com.aegerm.springbootrestcars.configuration.security.jwt;

import com.aegerm.springbootrestcars.domain.User;
import com.aegerm.springbootrestcars.domain.dto.CredentialsDTO;
import com.aegerm.springbootrestcars.domain.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String AUTH_URL = "/api/v1/login";

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(AUTH_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            CredentialsDTO user = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
            String username = user.getLogin();
            String password = user.getPassword();

            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                throw new BadCredentialsException("Usuário ou senha inválidos!");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
            return this.authenticationManager.authenticate(authentication);
        } catch (IOException ex) {
            throw new BadCredentialsException(ex.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String token = JwtUtil.createToken(user);
        String json = UserDTO.create(user, token).toJson();
        write(response, HttpStatus.OK, json);
    }

    private static void write(HttpServletResponse response, HttpStatus status, String json) throws IOException {
        response.setStatus(status.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(json);
    }
}
