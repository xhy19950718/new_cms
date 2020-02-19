package com.xiehongyuan.service;

import java.util.List;

import com.xiehongyuan.pojo.Like;

public interface LikeService {

	List<Like> list(Integer id);

	boolean dellike(Integer id);

	boolean doadd(Like like);

}
