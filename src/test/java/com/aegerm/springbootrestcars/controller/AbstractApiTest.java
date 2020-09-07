package com.aegerm.springbootrestcars.controller;

import com.aegerm.springbootrestcars.SpringbootRestCarsApplication;
import com.aegerm.springbootrestcars.configuration.security.jwt.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringbootRestCarsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractApiTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected UserDetailsService userDetailsService;

    private String token = "";

    @BeforeEach
    public void setupTest() {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername("admin");
        assertNotNull(userDetails);

        token = JwtUtil.createToken(userDetails);
        assertNotNull(token);
    }

    <T> ResponseEntity<T> post(String url, Object body, Class<T> type) {
        HttpHeaders headers = getHeaders();
        return this.restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, headers), type);
    }

    <T> ResponseEntity<T> get(String url, Class<T> type) {
        HttpHeaders headers = getHeaders();
        return this.restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), type);
    }

    <T> ResponseEntity<T> delete(String url, Class<T> type) {
        HttpHeaders headers = getHeaders();
        return this.restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(headers), type);
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return headers;
    }
}
