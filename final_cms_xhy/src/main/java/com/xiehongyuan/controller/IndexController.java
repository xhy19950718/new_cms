package com.xiehongyuan.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.ContinueRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.xiehongyuan.pojo.Article;
import com.xiehongyuan.pojo.Category;
import com.xiehongyuan.pojo.Channel;
import com.xiehongyuan.pojo.Slide;
import com.xiehongyuan.pojo.User;
import com.xiehongyuan.service.ArticleService;
import com.xiehongyuan.service.SlideService;
import com.xiehongyuan.service.UserService;

import scala.annotation.tailrec;

@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private KafkaTemplate kafkaTemplate;
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate; 
	@Autowired
	private RedisTemplate<String,Article> redisTemplate2;
	
	@RequestMapping(value="/")
	public String index(Model model) {
		return index(1, model);
	}
	
	@RequestMapping(value="/hot/{pageNum}.html")
	public String index(@PathVariable Integer pageNum, Model model) {
		/** Ƶ�� */
		if(pageNum==null) {
			pageNum=1;
		}
		if(pageNum==1) {
			ListOperations<String, Article> opsForList = redisTemplate2.opsForList();
			Long size = opsForList.size("hot");
			if(size>0) {
				List<Article> list = opsForList.range("hot", 0, -1);
				model.addAttribute("newArticleList", list);
			}else {
				List<Article> newArticleList = articleService.getNewList(6);
				model.addAttribute("newArticleList", newArticleList);
				for (Article article : newArticleList) {
					opsForList.leftPush("hot", article);
				}
				redisTemplate2.expire("hot", 300, TimeUnit.SECONDS);
			}
			
		}
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** �ֲ�ͼ */
		List<Slide> slideList = slideService.getAll();
		model.addAttribute("slideList", slideList);
		
		/** �ȵ����� **/
		
		PageInfo<Article> pageInfo =  articleService.getHotList(pageNum);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	
	
	@RequestMapping("/{channelId}/{cateId}/{pageNo}.html")
	public String channel(@PathVariable Integer channelId, Model model,
			@PathVariable Integer cateId,@PathVariable Integer pageNo) {
		/** Ƶ�� */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** ���� */
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		model.addAttribute("cateList", cateList);
		PageInfo<Article> pageInfo =  articleService.getListByChannelIdAndCateId(channelId,cateId,pageNo);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	
	@RequestMapping("article/{id}.html")
	public String articleDetail(@PathVariable Integer id,Model model,HttpSession session) throws UnknownHostException {
		/** ��ѯ���� **/
		
		Article article = articleService.getById(id);
		
			try {
				if(article!=null) {
					Integer articleId = article.getId();
					String ip = InetAddress.getLocalHost().getHostAddress();
					String token="hits_"+articleId+ip;
					ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
					String tokenInfo = opsForValue.get(token);
					System.err.println(tokenInfo);
					if(tokenInfo==null) {
						System.err.println("执行添加");
						opsForValue.set(token,"1",10,TimeUnit.SECONDS);
						articleService.upCnt(articleId);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("123");
			}
		
//		kafkaTemplate.sendDefault("articleById", id.toString());
		model.addAttribute("article", article);
		/** ��ѯ�û� **/
		User user = userService.getById(article.getUserId());
		model.addAttribute("user", user);
		/** ��ѯ������� **/
		List<Article> articleList = articleService.getListByChannelId(article.getChannelId(),id,10);
		model.addAttribute("articleList", articleList);
		
		
		
		return "article/detail";
	}
}
