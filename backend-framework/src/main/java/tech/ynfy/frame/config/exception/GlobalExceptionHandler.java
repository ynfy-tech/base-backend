package tech.ynfy.frame.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * 
 * @author yyx
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView baseException(Exception e)
    {
        log.error(e.getMessage(), e);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", e.getMessage());
        return mv;
    }
}
