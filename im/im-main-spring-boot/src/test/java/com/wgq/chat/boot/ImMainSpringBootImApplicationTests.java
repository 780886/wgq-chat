package com.wgq.chat.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class ImMainSpringBootImApplicationTests {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Test
	void contextLoads() {
	}


	@Test
	public void test2(){
		Boolean hello = stringRedisTemplate.opsForZSet().add("hello", "1",1);
		System.out.println("hello = " + hello);
	}

}
