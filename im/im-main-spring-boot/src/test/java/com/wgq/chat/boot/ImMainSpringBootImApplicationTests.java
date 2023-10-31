package com.wgq.chat.boot;

import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class ImMainSpringBootImApplicationTests {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Resource
	private MessageRepository messageRepository;

	@Test
	void contextLoads() {
	}


	@Test
	public void test2(){
		Boolean hello = stringRedisTemplate.opsForZSet().add("hello", "1",1);
		System.out.println("hello = " + hello);
	}

	@Test
	public void testUpdateById(){
		MessageBO messageBO = new MessageBO();
		messageBO.setId(24L);
		this.messageRepository.updateById(messageBO);
		System.out.println(1);
	}

}
