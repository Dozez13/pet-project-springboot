package com.example.task16.web.exceptionhandler;

//import com.example.finalprojectpm.db.exception.ApplicationEXContainer;

//@ControllerAdvice
//public class GlobalControllerExceptionHandler {
//
//    @ExceptionHandler(ApplicationEXContainer.ApplicationSendRegistrationMessageException.class)
//    public ModelAndView handleError(HttpServletRequest req, Exception ex, RedirectAttributes redirectAttributes) {
//        ModelAndView mav = new ModelAndView("redirect:/registration");
//        redirectAttributes.addFlashAttribute("registrationMessage", ex.getMessage());
//        return mav;
//    }
//
//    @ExceptionHandler(ApplicationEXContainer.ApplicationSendOrderMessageException.class)
//    public ModelAndView handleErrorOrder(HttpServletRequest req, Exception ex, RedirectAttributes redirectAttributes) {
//        ModelAndView mav = new ModelAndView("redirect:/user/order");
//        redirectAttributes.addFlashAttribute("NotAvailable", "fail");
//        redirectAttributes.addFlashAttribute("orderData", ex.getMessage());
//        return mav;
//
//    }
//
//
//
//}
