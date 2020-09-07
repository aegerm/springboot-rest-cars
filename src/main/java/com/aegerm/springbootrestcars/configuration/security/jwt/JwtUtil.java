package com.aegerm.springbootrestcars.configuration.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final String JWT_SECRET = ")H@McQfTjWnZr4u7w!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+KbPeShVmYq3t6w9z$";

    public static Claims getClaims(String token) {
        byte[] key = JWT_SECRET.getBytes();
        token = token.replace("Bearer", "");
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public static String getLogin(String token) {
        Claims claims = getClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    public static List<GrantedAuthority> getRoles(String token) {
        Claims claims = getClaims(token);
        return claims == null ? null : ((List<?>) claims.get("rol")).stream().map(auth -> new SimpleGrantedAuthority((String) auth)).collect(Collectors.toList());
    }

    public static String createToken(UserDetails details) {
        List<String> roles = details.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        byte[] key = JWT_SECRET.getBytes();
        int days = 10;
        long time  = days * 24 * 60 * 60 * 1000;

        Date expiration = new Date(System.currentTimeMillis() + time);

        return Jwts.builder().signWith(Keys.hmacShaKeyFor(key), SignatureAlgorithm.HS512)
                             .setSubject(details.getUsername())
                             .setExpiration(expiration)
                             .claim("rol", roles)
                             .compact();
    }

    public static boolean isTokenValid(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            String login = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            return login != null && expiration != null && now.before(expiration);
        }
        return false;
    }

    public static String getAuthLogin() {
        UserDetails user = getUserDetails();
        return user != null ? user.getUsername() : null;
    }

    public static UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
