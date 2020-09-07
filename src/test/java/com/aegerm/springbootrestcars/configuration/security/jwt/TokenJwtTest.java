package com.aegerm.springbootrestcars.configuration.security.jwt;

import com.aegerm.springbootrestcars.SpringbootRestCarsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringbootRestCarsApplication.class)
public class TokenJwtTest {

    @Autowired
    private UserDetailsService detailsService;

    @Test
    public void tokenTest() {
        UserDetails user = this.detailsService.loadUserByUsername("admin");
        assertNotNull(user);

        String jwtToken = JwtUtil.createToken(user);
        assertNotNull(jwtToken);

        assertTrue(JwtUtil.isTokenValid(jwtToken));

        assertEquals("admin", JwtUtil.getLogin(jwtToken));

        List<GrantedAuthority> roles = JwtUtil.getRoles(jwtToken);
        assertNotNull(roles);

        String role = roles.get(0).getAuthority();
        assertEquals(role, "ROLE_ADMIN");
    }
}
