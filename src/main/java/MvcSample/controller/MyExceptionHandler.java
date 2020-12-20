package MvcSample.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView  error(Exception ex) {
        ModelAndView  mov = new ModelAndView();
        mov.addObject("error_message", ex.getMessage());
        mov.setViewName("error");
        return mov;
    }
}
