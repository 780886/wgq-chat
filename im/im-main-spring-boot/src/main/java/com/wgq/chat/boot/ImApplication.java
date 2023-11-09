package com.wgq.chat.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @ClassName ImApplication
 * @Description TODO
 * @Author wgq
 * @Date 2023/8/24 19:21
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"com.wgq.*","com.sheep.*"})
@MapperScan(basePackages = {"com.wgq.chat.dao","com.wgq.chat.contact.dao","com.wgq.passport.dao"})
public class ImApplication {

    private static Logger logger = LoggerFactory.getLogger(ImApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ImApplication.class);

        springApplication.addListeners(new ApplicationListener<ApplicationStartingEvent>() {
            @Override
            public void onApplicationEvent(ApplicationStartingEvent event) {
                logger.info("prepare before bean init");
            }
        });

        springApplication.addListeners(new ApplicationListener<ContextRefreshedEvent>() {
            @Override
            public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
                logger.info("application startup at {}", contextRefreshedEvent.getTimestamp());
            }
        });

        springApplication.addListeners(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
                logger.info("application closed at at {}", contextClosedEvent.getTimestamp());
            }
        });
        springApplication.run(args);
    }
}
