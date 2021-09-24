package com.example.task16.web.controller;


import com.example.task16.service.RegistrationService;
import com.example.task16.service.dto.ProfileDto;
import com.example.task16.service.dto.RoleDto;
import com.example.task16.service.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Collections;

@Controller
public class RegistrationController {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class);
    private final RegistrationService taxiServiceRegistration;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(RegistrationService taxiServiceRegistration, PasswordEncoder passwordEncoder) {
        this.taxiServiceRegistration = taxiServiceRegistration;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/guest/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/guest/doRegistration")
    public ModelAndView doRegistration(@RequestParam("firstName") String firstName, @RequestParam("surName") String surName, @RequestParam("login") String login, @RequestParam("Email") String email,
                                       @RequestParam("psw") String psw) {
        LOGGER.info("DoRegistrationAction is invoked");
        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setPassword(passwordEncoder.encode(psw));
        userDto.setUserEmail(email);
        RoleDto roleDto = new RoleDto();
        roleDto.setUserAuthority("ROLE_CLIENT");
        userDto.setRoles(Collections.singleton(roleDto));
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUserRegistrationDate(LocalDateTime.now());
        profileDto.setUserFirstName(firstName);
        profileDto.setUserSurName(surName);
        profileDto.setAccountBalance(5000.0);
        userDto.setProfile(profileDto);
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        taxiServiceRegistration.doRegistration(userDto);
        return modelAndView;
    }
}
