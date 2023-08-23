package com.wgq.chat.boot;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wgq.*","com.sheep.*"},exclude = DruidDataSourceAutoConfigure.class)
@MapperScan(basePackages = {"com.wgq.passport.dao"})
public class ImApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImApplication.class, args);
	}

}
