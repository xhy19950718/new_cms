package com.xiehongyuan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiehongyuan.common.CmsConstant;
import com.xiehongyuan.common.JsonResult;
import com.xiehongyuan.pojo.Comment;
import com.xiehongyuan.pojo.Tousu;
import com.xiehongyuan.pojo.User;
import com.xiehongyuan.service.TousuService;

@Controller
@RequestMapping("/tousu/")
public class TousuController {
	
	@Autowired
	private TousuService tousuService;
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public @ResponseBody JsonResult add(Tousu tousu,HttpSession session) {
		System.err.println(tousu);
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo==null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode, "用户未登录");
		}
		tousu.setUserid(userInfo.getId());
		System.err.println(tousu);
		boolean result = tousuService.add(tousu);
		if(result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(10000, "未知错误");
	}
}
