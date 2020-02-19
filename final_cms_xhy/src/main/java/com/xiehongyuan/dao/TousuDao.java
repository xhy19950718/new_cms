package com.xiehongyuan.dao;

import org.apache.ibatis.annotations.Param;

import com.xiehongyuan.pojo.Tousu;

public interface TousuDao {

	int insert(@Param("tousu")Tousu tousu);

}
