package com.example.task16.web.controller;


import com.example.task16.service.UserService;
import com.example.task16.service.dto.ProfileDto;
import com.example.task16.service.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class ProfileController {
    private static final Logger LOGGER = LogManager.getLogger(ProfileController.class);
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/profile")
    public ModelAndView profilePage(Principal principal) {
        LOGGER.info("ProfileAction is invoked");
        ModelAndView modelAndView = new ModelAndView("profile");
        UserDto user = userService.find(principal.getName());
        LOGGER.info("User has id {}", user.getUserId());
        ProfileDto profile = user.getProfile();
        modelAndView.addObject("profileFirstName", profile.getUserFirstName());
        LOGGER.info("User has profileFirstName : {}", profile.getUserFirstName());
        modelAndView.addObject("profileSurName", profile.getUserSurName());
        LOGGER.info("User has profileSurName : {}", profile.getUserSurName());
        modelAndView.addObject("profileRegistrationDate", profile.getUserRegistrationDate().toString());
        LOGGER.info("User has registration date : {}", profile.getUserRegistrationDate());
        modelAndView.addObject("profileAccountBalance", profile.getAccountBalance());
        return modelAndView;
    }

    @GetMapping("/user/addMoney")
    public String addMoneyPage() {
        return "addMoney";
    }

    @PostMapping("/user/addMoneyM")
    public ModelAndView addMoneyAction(@RequestParam("amountM") int amount,Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        userService.addBalance(principal.getName(),amount);
        return modelAndView;

    }
}
