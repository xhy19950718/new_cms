package com.xiehongyuan.dao;

import java.util.List;

import com.xiehongyuan.pojo.Like;

public interface LikeDao {

	List<Like> list(Integer id);

	int dellike(Integer id);

	int doadd(Like like);

}
