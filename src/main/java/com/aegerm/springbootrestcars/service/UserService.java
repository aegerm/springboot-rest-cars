package com.aegerm.springbootrestcars.service;

import com.aegerm.springbootrestcars.domain.User;
import com.aegerm.springbootrestcars.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findUsers();

    UserDTO insertUser(User user);
}
