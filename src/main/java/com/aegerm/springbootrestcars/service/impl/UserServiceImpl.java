package com.aegerm.springbootrestcars.service.impl;

import com.aegerm.springbootrestcars.domain.User;
import com.aegerm.springbootrestcars.domain.dto.UserDTO;
import com.aegerm.springbootrestcars.repository.UserRepository;
import com.aegerm.springbootrestcars.service.RoleService;
import com.aegerm.springbootrestcars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public List<UserDTO> findUsers() {
        return this.userRepository.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
    }

    @Override
    public UserDTO insertUser(User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(password);
        user.getRoles().add(this.roleService.findByName("ROLE_USER"));

        return (UserDTO.create(this.userRepository.save(user)));
    }
}
