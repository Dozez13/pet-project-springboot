package com.example.task16.service;

import com.example.task16.service.dto.UserDto;

public interface UserService {
    boolean addBalance(String login, double accountBalance);
    UserDto find(String login);
}
