package com.aegerm.springbootrestcars.configuration.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");

        if (StringUtils.isEmpty(token) || ! token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            if (! JwtUtil.isTokenValid(token)) {
                throw new AccessDeniedException("Acesso negado.");
            }

            String login = JwtUtil.getLogin(token);

            UserDetails details = this.userDetailsService.loadUserByUsername(login);

            List<GrantedAuthority> authorities = JwtUtil.getRoles(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(details, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        } catch (RuntimeException exception) {
            logger.error("Authentication error: " + exception.getMessage(), exception);
            throw exception;
        }
    }
}
