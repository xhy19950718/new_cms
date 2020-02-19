package com.xiehongyuan.pojo;

import java.io.Serializable;

public class ArticleTag  implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(这个变量表示�?�?)   
	 */  
	private static final long serialVersionUID = 1L;

	private Integer aid;

    private Integer tid;
    
    @Override
	public String toString() {
		return "ArticleTag [aid=" + aid + ", tid=" + tid + "]";
	}

	public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}