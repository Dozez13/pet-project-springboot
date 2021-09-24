package com.example.task16.service;

import com.example.task16.db.entity.Profile;
import com.example.task16.db.entity.User;
import com.example.task16.db.repository.UserRepository;
import com.example.task16.service.assembler.Assembler;
import com.example.task16.service.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private Assembler<User, UserDto> userDtoAssembler;

    public UserServiceImpl(UserRepository userRepository, Assembler<User, UserDto> userDtoAssembler) {
        this.userRepository = userRepository;
        this.userDtoAssembler = userDtoAssembler;
    }

    @Override
    public boolean addBalance(String login, double accountBalance) {
        User user = userRepository.findByLogin(login);
        Profile profile = user.getProfile();
        profile.setAccountBalance(profile.getAccountBalance()+accountBalance);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto find(String login) {
        return userDtoAssembler.mergeAggregateIntoDto(userRepository.findByLogin(login));
    }
}
