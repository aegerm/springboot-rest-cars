package com.aegerm.springbootrestcars.service;

import com.aegerm.springbootrestcars.domain.Role;

public interface RoleService {

    Role findByName(String name);
}
