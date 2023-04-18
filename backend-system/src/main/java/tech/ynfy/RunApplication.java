package tech.ynfy;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
@MapperScan(value={"tech.ynfy.module.**.mapper"})
public class RunApplication {
    
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(RunApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostName();
        String port = env.getProperty("server.port");
        String context = env.getProperty("server.servlet.context-path");
        log.warn("\n----------------------------------------------------------\n\t" +
                "Application gtkj-Boot is running! Access URLs:\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + context + "/doc.html\n" +
                "----------------------------------------------------------");

        log.info("----------@@@@@@@@@@@@@@@@@  启动成功 @@@@@@@@@@@@@ --------------");
    }

//    @PreDestroy
//    public void destory() {
//        log.info("服务结束，开始释放空间...");
//        // 结束正在进行的任务
//        Set<String> keys = CameraApi.JOBMAP.keySet();
//        for (String key : keys) {
//            CameraApi.JOBMAP.get(key).setInterrupted(key);
//        }
//        // 关闭线程池
//        CameraThread.MyRunnable.es.shutdown();
//        // 销毁定时器
//        CameraTimer.timer.cancel();
//    }
}
