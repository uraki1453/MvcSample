package MvcSample.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AppExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView  error(Exception ex) {
        logger.error(ex.getMessage(),ex);

        ModelAndView  mov = new ModelAndView();
        mov.addObject("error_message", ex.getMessage());
        mov.setViewName("error");
        return mov;
    }
}
