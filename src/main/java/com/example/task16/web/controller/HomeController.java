package com.example.task16.web.controller;


import com.example.task16.service.CarCategoryService;
import com.example.task16.service.dto.CarCategoryDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
    private final CarCategoryService carCategoryService;

    @Autowired
    public HomeController(CarCategoryService carCategoryService ) {
        this.carCategoryService = carCategoryService;

    }

    @GetMapping({"/index", "/"})
    public ModelAndView homePage() {
        LOGGER.info("HomeAction is invoked");
        ModelAndView modelAndView = new ModelAndView("index");
        List<CarCategoryDto> carCategories = carCategoryService.findExistingCarC();
        LOGGER.debug("Get Existing CarCategories, number Is {}", carCategories.size());

        modelAndView.addObject("categories", carCategories);


        return modelAndView;
    }
}
