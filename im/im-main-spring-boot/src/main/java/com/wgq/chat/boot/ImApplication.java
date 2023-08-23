package com.wgq.chat.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(scanBasePackages = {"com.wgq.*","com.sheep.*"})
@MapperScan(basePackages = {"com.wgq.chat.dao","com.wgq.passport.dao"})
public class ImApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ImApplication.class, args);
		Object userDao = applicationContext.getBean("userDao");
		System.out.println(userDao);
	}

}
