package com.aegerm.springbootrestcars.service.impl;

import com.aegerm.springbootrestcars.configuration.security.UserPrincipal;
import com.aegerm.springbootrestcars.domain.User;
import com.aegerm.springbootrestcars.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = this.userRepository.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException(login);
        }

        return new UserPrincipal(user.getId(), user.getLogin(), user.getPassword(), user.getRoles());
    }
}
