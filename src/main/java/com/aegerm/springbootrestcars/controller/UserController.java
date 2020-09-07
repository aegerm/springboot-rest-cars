package com.aegerm.springbootrestcars.controller;

import com.aegerm.springbootrestcars.domain.User;
import com.aegerm.springbootrestcars.domain.dto.UserDTO;
import com.aegerm.springbootrestcars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> userDTOS = this.userService.findUsers();
        return ResponseEntity.ok(userDTOS);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> insertUser(@RequestBody User user) {
        UserDTO dto = this.userService.insertUser(user);
        return ResponseEntity.created(getUri(dto.getId())).build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
