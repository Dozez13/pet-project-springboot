package com.example.task16.service;

import com.example.task16.db.entity.User;
import com.example.task16.db.repository.UserRepository;
import com.example.task16.service.assembler.Assembler;
import com.example.task16.service.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    private UserRepository userRepository;
    private Assembler<User,UserDto> userDtoAssembler;

    public RegistrationServiceImpl(UserRepository userRepository, Assembler<User, UserDto> userDtoAssembler) {
        this.userRepository = userRepository;
        this.userDtoAssembler = userDtoAssembler;
    }

    @Override
    public void doRegistration(UserDto userDto) {
      userRepository.save(userDtoAssembler.mergeDtoIntoAggregate(userDto));
    }
}
