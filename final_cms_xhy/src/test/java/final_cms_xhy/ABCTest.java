package final_cms_xhy;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.xiehongyuan.dao.ArticleDao;
import com.xiehongyuan.pojo.Article;
import com.xiehongyuan.service.ArticleService;
import com.xiehongyuan.service.RedisArticleService;
import com.xiehongyuan.util.FileUtil;
import com.xiehongyuan.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ABCTest {
	
	
	@Autowired
	private RedisArticleService redisArticleService;
	@Test
	public void dsfd() {
		File file=new File("C:\\Users\\67182\\Desktop\\myJsoup");
		String[] list = file.list();
		for (String string : list) {
			Article article = new Article();
			String[] split = string.split("\\.");
			article.setTitle(split[0]);
			String readTextFileByLine = FileUtil.readTextFileByLine("C:\\Users\\67182\\Desktop\\myJsoup\\"+string);
			article.setContent(readTextFileByLine);
			article.setChannelId(1);
			article.setCategoryId(1);
			article.setUserId(178);
			article.setHits(1);
			article.setHot(1);
			article.setStatus(1);
			article.setDeleted(0);
			article.setCreated(new Date());
			article.setUpdated(new Date());
			article.setCommentcnt(0);
			System.err.println(article);
			redisArticleService.addredis(article);
		}
		
	}
	
	
	@Test
	public void url() {
		String url="http://192.168.11.110:8080/article/74.html";
		boolean httpUrl = StringUtil.isHttpUrl(url);
		if(httpUrl) {
			System.err.println("success");
		}else {
			System.err.println("eorro");
		}
	}
}
