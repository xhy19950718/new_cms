package com.xiehongyuan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.xiehongyuan.pojo.Article;

@Service
public class RedisArticleService {
	
	@Autowired
	private RedisTemplate<String, Article> redisTemplate;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	public boolean addredis(Article article) {
		ValueOperations<String, Article> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(article.getTitle(), article);
		kafkaTemplate.sendDefault("add", article.getTitle());
		return true;
	}
}
