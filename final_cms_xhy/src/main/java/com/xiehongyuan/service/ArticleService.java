package com.xiehongyuan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.xiehongyuan.pojo.Article;
import com.xiehongyuan.pojo.Category;
import com.xiehongyuan.pojo.Channel;
public interface ArticleService {
	/**
	 * @Title: getPageInfo   
	 * @Description: ��ҳ�б�   
	 * @param: @param user
	 * @param: @param pageNum
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: PageInfo<Article>      
	 * @throws
	 */
	boolean addTousu(Integer id);
	
	PageInfo<Article> getPageInfo(Article article, int pageNum,int pageSize); 
	/**
	 * @Title: updateStatus   
	 * @Description: �޸�����״̬   
	 * @param: @param id
	 * @param: @param status
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateStatus(Integer id,int status);
	/**
	 * @Title: addHot   
	 * @Description: ���ȣ�ÿ�θ�hotֵ��1   
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addHot(Integer id);
	/**
	 * @Title: getChannelList   
	 * @Description: ��ѯƵ���б�   
	 * @param: @return      
	 * @return: List<Channel>      
	 * @throws
	 */
	public List<Channel> getChannelList();
	/**
	 * @Title: getById   
	 * @Description: ����id��ѯ���� 
	 * @param: @param id
	 * @param: @return      
	 * @return: Article      
	 * @throws
	 */
	public Article getById(Integer id);
	/**
	 * @Title: save   
	 * @Description: ������޸����� 
	 * @param: @param article
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean save(Article article);
	/**
	 * @Title: getCateListByChannelId   
	 * @Description: ����Ƶ��Id��ѯ�����б�  
	 * @param: @param channelId
	 * @param: @return      
	 * @return: List<Category>      
	 * @throws
	 */
	List<Category> getCateListByChannelId(Integer channelId);
	/**
	 * @Title: delByIds   
	 * @Description: ����ɾ��   
	 * @param: @param ids
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean delByIds(String ids);
	/**
	 * @Title: isAllCheck   
	 * @Description: ����Ids�ж������Ƿ������   
	 * @param: @param ids
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean isAllCheck(String ids);
	/**
	 * @Title: getListByChannelId   
	 * @Description: ����Ƶ��Id��ѯ�����б�   
	 * @param: @param channelId
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> getListByChannelId(Integer channelId, Integer id, int num);
	/**
	 * @Title: getHotList   
	 * @Description: �ȵ�����   
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: PageInfo<Article>      
	 * @throws
	 */
	PageInfo<Article> getHotList(int pageNum);
	/**
	 * @Title: getListByChannelIdAndCateId   
	 * @Description: ����Ƶ��Id�ͷ���Id��ѯ�����б�  
	 * @param: @param channelId
	 * @param: @param cateId
	 * @param: @param pageNo
	 * @param: @return      
	 * @return: PageInfo<Article>      
	 * @throws
	 */
	PageInfo<Article> getListByChannelIdAndCateId(Integer channelId, Integer cateId, Integer pageNo);
	/**
	 * 
	 * @param num
	 * @return
	 */
	List<Article> getNewList(int num);

	void upCnt(Integer id);
}



