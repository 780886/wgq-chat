package com.wgq.chat.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wgq.*","com.sheep.*"})
public class ImApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImApplication.class, args);
	}

}
