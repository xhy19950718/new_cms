package com.xiehongyuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiehongyuan.dao.LikeDao;
import com.xiehongyuan.pojo.Like;
import com.xiehongyuan.service.LikeService;
@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDao likeDao;
	
	@Override
	public List<Like> list(Integer id) {
		// TODO Auto-generated method stub
		return likeDao.list(id);
	}

	@Override
	public boolean dellike(Integer id) {
		// TODO Auto-generated method stub
		return likeDao.dellike(id)>0;
	}

	@Override
	public boolean doadd(Like like) {
		// TODO Auto-generated method stub
		return likeDao.doadd(like)>0;
	}

}
