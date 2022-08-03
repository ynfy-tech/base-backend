package tech.ynfy.frame.config.task;

import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 线程上下文copy
 * 解决一些请求异步出来线程切换丢失上下文信息
 * 比如用户认证信息，调用链监控信息等
 *
 * 
 */
// https://stackoverflow.com/questions/23732089/how-to-enable-request-scope-in-async-task-executor
public class ContextCopyingDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        try {
            RequestAttributes context = RequestContextHolder.currentRequestAttributes();
            return () -> {
                try {
                    RequestContextHolder.setRequestAttributes(context);
                    runnable.run();
                } finally {
                    RequestContextHolder.resetRequestAttributes();
                }
            };
        } catch (Exception e) {//处理非容器类线程绑定不了的情况
            return () -> {
                runnable.run();
            };
        }
    }
}
