package tech.ynfy.frame.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常处理器
 * 
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(Exception.class)
    public void baseException(Exception e, HttpServletResponse response) throws IOException {
        String msg = "error: " + e.getMessage();
        log.error(msg, e);

        //返回结果
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        // 获取PrintWriter对象
        PrintWriter out = response.getWriter();
        out.print(msg);
        // 释放PrintWriter对象
        out.flush();
        out.close();
    }
}
