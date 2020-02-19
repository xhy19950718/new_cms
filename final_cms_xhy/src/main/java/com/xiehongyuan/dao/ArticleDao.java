package com.xiehongyuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiehongyuan.pojo.Article;

public interface ArticleDao {
	/**
	 * @Title: selectById   
	 * @Description: ����Id����ѯ����   
	 * @param: @param id
	 * @param: @return      
	 * @return: User      
	 * @throws
	 */
	Article selectById(@Param("id") Integer id);
	/**
	 * @Title: select   
	 * @Description: ����User��ѯ�б�  
	 * @param: @param user
	 * @param: @return      
	 * @return: List<User>      
	 * @throws
	 */
	List<Article> select(@Param("article") Article article);
	/**
	 * @Title: count   
	 * @Description: ��ѯ��������   
	 * @param: @param user
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int count(@Param("article") Article article);
	/**
	 * @Title: insert   
	 * @Description: ����һ����¼   
	 * @param: @param user
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int insert(@Param("article") Article article);
	/**
	 * @Title: update   
	 * @Description: ����Id���¼�¼ 
	 * @param: @param user
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int update(@Param("article") Article article);
	/**
	 * @Title: deleteById   
	 * @Description: ����Idɾ����¼   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int updateDeletedById(@Param("id") Integer id);
	/**
	 * @Title: deleteByIds   
	 * @Description: ����Ids����ɾ����¼   
	 * @param: @param ids "1,2,3"
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int updateDeletedByIds(@Param("ids") String ids);
	/**
	 * @Title: updateStatus   
	 * @Description: �޸�����״̬   
	 * @param: @param id
	 * @param: @param status
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int updateStatus(@Param("id") Integer id,@Param("status") Integer status);
	/**
	 * @Title: addHot   
	 * @Description: �ȵ��ֶ�+1   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int addHot(@Param("id") Integer id);
	/**
	 * @Title: selectByIds   
	 * @Description: ����Ids��ѯ����  
	 * @param: @param ids
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> selectByIds(@Param("ids") String ids);
	/**
	 * @Title: selectListByChannelId   
	 * @Description: ����Ƶ��Id��ѯ����   
	 * @param: @param channelId
	 * @param: @param aritcleId ����Id���ܵ��ڸ�Id
	 * @param: @param num ��������
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> selectListByChannelId(@Param("channelId") Integer channelId, @Param("articleId") Integer articleId, @Param("num") int num);
	/**
	 * @Title: selectByHot   
	 * @Description: ��ѯ�ȵ�����   
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> selectByHot();
	/**
	 * @Title: selectListByChannelIdAndCateId   
	 * @Description: ����Ƶ��Id�ͷ���Id��ѯ����   
	 * @param: @param channelId
	 * @param: @param cateId
	 * @param: @param pageNo
	 * @param: @return      
	 * @return: List<Article>      
	 * @throws
	 */
	List<Article> selectListByChannelIdAndCateId(@Param("channelId") Integer channelId, @Param("cateId") Integer cateId);

	/**
	 * 
	 * @param num
	 * @return
	 */
	List<Article> selectNewList(@Param("num") int num);
	int addTousu(Integer id);
	void upCnt(Integer id);
}
