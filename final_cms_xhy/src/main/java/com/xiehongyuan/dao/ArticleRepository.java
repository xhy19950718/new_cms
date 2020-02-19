package com.xiehongyuan.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.xiehongyuan.pojo.Article;



/**
 * 此时就具备了CRUD的功能
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Integer>{
   
	//实现复杂查询
		//按照标题查询,方法名称一定要按照规则写
		List<Article> findByTitle(String key);
		//按照标题或者内容查询,方法名称一定要按照规则写
//		List<ArticleWithBLOBs> findByTitleOrContent(String title,String content);
}
