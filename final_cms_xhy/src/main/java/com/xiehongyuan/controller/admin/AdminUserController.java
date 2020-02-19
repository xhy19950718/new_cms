package com.xiehongyuan.controller.admin;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiehongyuan.common.CmsConstant;
import com.xiehongyuan.common.CmsMd5Util;
import com.xiehongyuan.common.JsonResult;
import com.xiehongyuan.pojo.User;
import com.xiehongyuan.service.UserService;
import com.zhanggm.common.utils.StringUtil;

@Controller
@RequestMapping("/admin/user/")
public class AdminUserController {
	@Autowired
	private UserService userService;
	/**
	 * @Title: login   
	 * @Description: ��̨��¼�ӿ�   
	 * @param: @param user
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping("login")
	@ResponseBody
	public Object login(User user,HttpSession session) {
		//�ж��û���������
		if(StringUtil.isBlank(user.getUsername()) || StringUtil.isBlank(user.getPassword())) {
			return JsonResult.fail(1000, "�û��������벻��Ϊ��");
		}
		//��ѯ�û�
		User userInfo = userService.getByUsername(user.getUsername());
		//�û�Ϊ��
		if(userInfo==null) {
			return JsonResult.fail(1000, "�û������������");
		}
		//�Ƿ����Ա
		if(!userInfo.isAdmin()) {
			return JsonResult.fail(1000, "Ȩ�޲���");
		}
		//�ж�����
		String string2md5 = CmsMd5Util.string2MD5(user.getPassword());
		if(string2md5.equals(userInfo.getPassword())) {
			session.setAttribute(CmsConstant.UserAdminSessionKey, userInfo);
			return JsonResult.sucess();
		}
		return JsonResult.fail(500, "δ֪����");
	}
	
	@RequestMapping("logout")
	public Object logout(HttpServletResponse response,HttpSession session) {
		session.removeAttribute(CmsConstant.UserAdminSessionKey);
		return "redirect:/admin/";
	}
}
