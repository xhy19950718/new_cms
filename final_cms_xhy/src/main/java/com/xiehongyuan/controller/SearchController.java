package com.xiehongyuan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.xiehongyuan.common.HLUtils;
import com.xiehongyuan.pojo.Article;
import com.xiehongyuan.pojo.Channel;
import com.xiehongyuan.service.ArticleService;


@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	ArticleService articleService;
	@RequestMapping("/search")
		public String search(String key,Article article, Model model, @RequestParam(defaultValue = "1") Integer page,
				@RequestParam(defaultValue = "2") Integer pageSize) {
		System.err.println(key);
			//显示栏目
			//0.封装查询条件
					article.setStatus(1);
					model.addAttribute("article", article);
					//1. 查询出所有的栏目
							List<Channel> channels = articleService.getChannelList();
							model.addAttribute("channelList", channels);
			//实现高亮显示
			PageInfo<Article> pageInfo = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, page, pageSize, new String[] {"title"}, "id", key);
			
			System.err.println(pageInfo);
			model.addAttribute("key", key);
			model.addAttribute("pageInfo", pageInfo);
			return "index";
	}
}
