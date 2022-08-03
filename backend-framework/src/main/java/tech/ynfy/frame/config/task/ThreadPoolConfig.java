package tech.ynfy.frame.config.task;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static tech.ynfy.frame.constant.ThreadConstant.*;

/**
 * 线程池配置
 **/
@Configuration
public class ThreadPoolConfig {

    // 核心线程池大小
    @Value("${ynfy.thread.corePoolSize}")
    private Integer corePoolSize;

    // 最大可创建的线程数
    @Value("${ynfy.thread.maxPoolSize}")
    private Integer maxPoolSize;

    // 队列最大长度
    @Value("${ynfy.thread.queueCapacity}")
    private Integer queueCapacity;

    // 线程池维护线程所允许的空闲时间
    @Value("${ynfy.thread.keepAliveSeconds}")
    private Integer keepAliveSeconds;

    @Bean(BASIC_THREAD)
    public Executor getAsyncExecutor() {
        return this.getThread();
    }

    /**
     * 执行周期性或定时任务
     */
    @Bean(SCHEDULED_THREAD)
    protected ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                                               new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d")
                                                                               .daemon(true)
                                                                               .build()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
            }
        };
    }

    /**
     * 对线程池做特殊处理, 用于异步线程 从 Spring Security 获取context等
     *
     * @return
     * @apiNote 异步线程中, multipartfile 不能传递, 只能传递流
     */
//    @Bean(ASYNC_THREAD)
//    public Executor getListenerAsyncExecutor() {
//        ThreadPoolTaskExecutor executor = this.getThread();
//        DelegatingSecurityContextAsyncTaskExecutor delegate = new DelegatingSecurityContextAsyncTaskExecutor(executor);
//        return delegate;
//    }
    
    private ThreadPoolTaskExecutor getThread() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //for passing in request scope context
        executor.setTaskDecorator(new ContextCopyingDecorator());
        //graceful shutdown
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(ASYNC_THREAD + "-");
        executor.initialize();
        return executor;
    }
}
