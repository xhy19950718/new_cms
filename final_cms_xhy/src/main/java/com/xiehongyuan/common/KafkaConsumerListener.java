package com.xiehongyuan.common;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.xiehongyuan.pojo.Article;
import com.xiehongyuan.service.ArticleService;

//消费者的监听器
@Component
public class KafkaConsumerListener implements MessageListener<String, String>{
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	RedisTemplate<String, Article> redisTemplate;
	@Override
	public void onMessage(ConsumerRecord<String, String> record) {
		System.out.println(record.key() + " ====" + record.value());
		if(record.key().equals("add")) {
			String name=record.value();
			SetOperations<String, Article> opsForSet = redisTemplate.opsForSet();
			Article pop = opsForSet.pop(name);
			System.err.println(pop);
			boolean save = articleService.save(pop);
			if(save) {
				opsForSet.remove(name);
			}
			
			System.err.println(pop+"添加成功");
			
		}
		
//		if(record.key().equals("articleById")) {
//			articleService.upCnt(Integer.parseInt(record.value()));
//		}
	}

}
