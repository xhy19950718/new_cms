package com.xiehongyuan.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xiehongyuan.dao.CommentDao;
import com.xiehongyuan.dao.UserDao;
import com.xiehongyuan.pojo.Comment;
import com.xiehongyuan.pojo.User;
import com.zhanggm.common.utils.DateUtil;
@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private UserDao userDao;
	/**
	 * @Title: add   
	 * @Description: 添加评论   
	 * @param: @param comment
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean add(Comment comment) {
		SimpleDateFormat sFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createdStr = sFormat.format(new Date());
		comment.setCreated(createdStr);
		return commentDao.insert(comment)>0;
	}
	/**
	 * @Title: getPageInfo   
	 * @Description: 根据文章Id,查询评论   
	 * @param: @param articleId
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: PageInfo<Comment>      
	 * @throws
	 */
	public PageInfo<Comment> getPageInfo(Integer articleId,int pageNum){
		PageHelper.startPage(pageNum, 2);
		Comment comment = new Comment();
		comment.setArticleid(articleId);
		
		List<Comment> commentList = commentDao.select(comment);
		
		return new PageInfo<>(commentList);
	}
}
