package com.example.task16.service;


import com.example.task16.db.entity.User;
import com.example.task16.db.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exist");
        }
        Set<GrantedAuthority> userRoles = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getUserAuthority())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), userRoles);
    }
}
