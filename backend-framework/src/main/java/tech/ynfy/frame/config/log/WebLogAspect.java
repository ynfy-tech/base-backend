package tech.ynfy.frame.config.log;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import tech.ynfy.frame.util.IPUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * tech.ynfy.module..*.*Controller.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut() {
    }


    /**
     * 前置通知
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) throws Throwable {
        if (ret instanceof ModelAndView) {
            // ModelAndView不打印
            return;
        }
        // 处理完请求，返回内容
        logger.debug("返回值 : " + JSON.toJSONString(ret));
    }


    /**
     * 后置通知
     *
     * @param pjp
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 耗时
        long startTime = System.currentTimeMillis();
        Object ob = null;// ob 为方法的返回值
        float excTime = 0;
        try {
            ob = pjp.proceed();
            excTime = (float) (System.currentTimeMillis() - startTime) / 1000;
        } catch (Throwable throwable) {
            // 交给全局异常捕获
            throw throwable;
        } finally {
            /**
             * 无论是否有异常都打印接口日志
             */
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String method = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
            String vars = Arrays.toString(pjp.getArgs());
            // 记录下请求内容 并发时会导致打印在多处, 所以改为一行
            String logStr = String.format("请求地址: %s; 方法: %s; IP: %s; 参数: %s; 耗时: %s秒",
                                          request.getRequestURL().toString(),
                                          method,
                                          IPUtils.getIpAddr(request),
                                          vars,
                                          excTime);
            logger.warn(logStr);
        }

        // return放在异常外
        return ob;
    }
}
