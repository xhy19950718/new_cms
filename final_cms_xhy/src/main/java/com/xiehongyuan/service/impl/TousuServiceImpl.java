package com.xiehongyuan.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiehongyuan.dao.TousuDao;
import com.xiehongyuan.pojo.Article;
import com.xiehongyuan.pojo.Tousu;
import com.xiehongyuan.service.ArticleService;
import com.xiehongyuan.service.TousuService;

import net.sf.jsqlparser.statement.select.Select;
@Service
public class TousuServiceImpl implements TousuService{

	@Autowired
	private TousuDao tousuDao;
	@Autowired
	private ArticleService articleService;
	@Override
	public boolean add(Tousu tousu) {
		SimpleDateFormat sFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createdStr = sFormat.format(new Date());
		tousu.setCreated(createdStr);
		if(tousuDao.insert(tousu)>0) {
			boolean flag=articleService.addTousu(tousu.getArticleid());
			Article byId = articleService.getById(tousu.getArticleid());
			if(byId.getTousuCnt()>50) {
				articleService.updateStatus(tousu.getArticleid(), 3);
			}
			return flag;
		}
		return false;
	}

	
}
