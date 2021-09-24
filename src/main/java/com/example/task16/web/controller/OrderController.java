package com.example.task16.web.controller;


import com.example.task16.service.CarCategoryService;
import com.example.task16.service.OrderService;
import com.example.task16.service.UserService;
import com.example.task16.service.dto.CarCategoryDto;
import com.example.task16.service.dto.OrderDto;
import com.example.task16.service.dto.UserDto;
import com.example.task16.service.util.ImageUtil;
import com.example.task16.web.model.ToggleButton;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class OrderController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);
    private final CarCategoryService carCategoryService;

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(CarCategoryService carCategoryService, OrderService orderService, UserService userService) {
        this.carCategoryService = carCategoryService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/user/order")
    public ModelAndView orderPage(Principal principal) throws JsonProcessingException {
        LOGGER.info("OrderGAction is invoked");

        ModelAndView modelAndView = new ModelAndView("order");
        List<CarCategoryDto> carCategories = carCategoryService.findExistingCarC();
        LOGGER.debug("Get Existing CarCategories, number Is {}", carCategories.size());
        List<ToggleButton> buttons = IntStream.range(0, carCategories.size())
                .mapToObj(i -> {
                    ToggleButton button = new ToggleButton();
                    button.setIcon("img:data:image/png;base64," + ImageUtil.getBase64String(carCategories.get(i).getCarCategoryImage()));
                    button.setValue(carCategories.get(i).getCarCategoryName());
                    button.setSlot(i + 1 + "");
                    return button;
                })
                .collect(Collectors.toList());
        LOGGER.debug("Creating List of Toggle Buttons");
        ObjectMapper mapper = new ObjectMapper();
        modelAndView.addObject("carCategories", carCategories);
        modelAndView.addObject("carCategoriesButtons", mapper.writeValueAsString(buttons));

        return modelAndView;
    }

    @PostMapping("/user/doOrder")
    public ModelAndView doOrder(Principal principal, @RequestParam("userAddress") String userAddress, @RequestParam("userDestination") String userDestination,
                                @RequestParam("numOfPas") String[] stingNumbers, @RequestParam("categories") String[] categories, RedirectAttributes redirectAttributes) {
        LOGGER.info("OrderMAction is invoked");
        ModelAndView modelAndView = new ModelAndView("redirect:/user/order");
        if (stingNumbers != null && stingNumbers.length > 0 && categories != null && categories.length > 0) {
            String message = orderService.makeOrder(stingNumbers, categories, userAddress, userDestination, principal.getName());
            redirectAttributes.addFlashAttribute("takenTime", message);
        }
        return modelAndView;
    }

    @GetMapping("/admin/orders")
    public ModelAndView allOrdersPage() {
        LOGGER.info("OrdersCountAction is invoked");
        ModelAndView modelAndView = new ModelAndView("orders");
        Long count = orderService.orderCount();
        LOGGER.info("Get Order count , count is {}", count);
        modelAndView.addObject("Count", count);
        return modelAndView;
    }

    @GetMapping("/admin/ordersJson")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<OrderDto> getOrders(@RequestParam("filter") OrderDto orderDto, Pageable pageable) throws JsonProcessingException {
        LOGGER.info("OrdersGetAction is invoked");
        List<OrderDto> getOrders = orderService.findFilSortOrders(orderDto,pageable);
        LOGGER.info("Filtered and Sorted Order count is {}", getOrders.size());
        return getOrders;
    }

    @GetMapping("/user/myOrders")
    public ModelAndView myOrdersPage(@RequestParam(value = "startRow", required = false) String startRowSTR,
                                     @RequestParam(value = "currentPage", required = false) String currentPageSTR,
                                     Principal principal) {
        LOGGER.info("MyOrders Action is invoked");
        ModelAndView modelAndView = new ModelAndView("myOrders");
        UserDto user = userService.find(principal.getName());
        int startRow = 0;
        int rowsPerPage = 3;
        int currentPage = 1;
        String strStartRow;
        String strCurrentPage;
        if ((strStartRow = startRowSTR) != null) {
            try {
                int temp = Integer.parseInt(strStartRow);
                startRow = Math.max(temp, 0);
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage());
               // throw new ApplicationEXContainer.ApplicationCantRecoverException(e.getMessage());
            }
        }
        if ((strCurrentPage = currentPageSTR) != null) {
            try {
                int temp = Integer.parseInt(strCurrentPage);
                currentPage = temp > 0 ? temp : 1;
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage());
                //throw new ApplicationEXContainer.ApplicationCantRecoverException(e.getMessage());
            }
        }
        List<OrderDto> orders = orderService.findOrdersByUser(user.getUserId(), PageRequest.of(startRow,rowsPerPage));
        Long orderCount = orderService.countUserOrder(user.getUserId());
        int numOfPages = (int) (Math.ceil(orderCount / (double) rowsPerPage));
        modelAndView.addObject("myOrders", orders);
        modelAndView.addObject("startRow", startRow);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("totalOrders", numOfPages);
        return modelAndView;
    }

}
