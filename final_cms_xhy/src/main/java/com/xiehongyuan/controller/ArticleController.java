package com.xiehongyuan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xiehongyuan.common.CmsConstant;
import com.xiehongyuan.common.JsonResult;
import com.xiehongyuan.pojo.Article;
import com.xiehongyuan.pojo.Category;
import com.xiehongyuan.pojo.Channel;
import com.xiehongyuan.pojo.User;
import com.xiehongyuan.service.ArticleService;
import com.xiehongyuan.service.RedisArticleService;

@Controller
@RequestMapping("/article/")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	KafkaTemplate kafkaTemplate;
	@Autowired
	private RedisArticleService redisArticleService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @Title: add   
	 * @Description: ��������   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Integer id,Model model) {
		logger.info("articleId:{}",id);
		if(id!=null) {
			Article article = articleService.getById(id);
			logger.info(article.toString());
			model.addAttribute("article", article);
			List<Category> cateList = articleService.getCateListByChannelId(article.getChannelId());
			model.addAttribute("cateList", cateList);
		}
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		return "article/add";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult add(Article article,Model model,HttpSession session) {
		System.out.println(article);
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo==null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode, "δ��¼");
		}
		article.setUserId(userInfo.getId());
		System.err.println(article);
//		String json = JSON.toJSONString(article);
//		kafkaTemplate.sendDefault("add", json);
		articleService.save(article);
		return JsonResult.sucess(true);
	}
	/**
	 * @Title: getCateList   
	 * @Description: ����Ƶ��Id��ѯ�����б�   
	 * @param: @param channelId
	 * @param: @param model
	 * @param: @param session
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@RequestMapping(value="getCateList",method=RequestMethod.GET)
	@ResponseBody
	public JsonResult getCateList(Integer channelId,Model model,HttpSession session) {
		return JsonResult.sucess(articleService.getCateListByChannelId(channelId));
	}
	/**
	 * @Title: delByIds   
	 * @Description: ����ɾ��   
	 * @param: @param ids
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@RequestMapping("delByIds")
	public @ResponseBody JsonResult delByIds(String ids) {
		if(ids==null) {
			return JsonResult.fail(10001, "��ѡ��ɾ��������");
		}
		//������ж�
		boolean isCheck = articleService.isAllCheck(ids);
		if(!isCheck) {
			return JsonResult.fail(10001, "��ѡ��δ��˵�����ɾ��");
		}
		//ɾ��
		boolean result = articleService.delByIds(ids);
		if(result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(500, "δ֪����");
	}
}
