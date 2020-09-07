package com.aegerm.springbootrestcars.service.impl;

import com.aegerm.springbootrestcars.domain.Role;
import com.aegerm.springbootrestcars.repository.RoleRepository;
import com.aegerm.springbootrestcars.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
