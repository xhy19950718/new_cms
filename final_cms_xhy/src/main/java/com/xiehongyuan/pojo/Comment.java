package com.xiehongyuan.pojo;

import java.io.Serializable;

public class Comment  implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(这个变量表示�?�?)   
	 */  
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer articleid;

    private Integer userid;

    private String content;

    private String created;
    
    private String nickname;
    
    private String headming;

    @Override
	public String toString() {
		return "Comment [id=" + id + ", articleid=" + articleid + ", userid=" + userid + ", content=" + content
				+ ", created=" + created + "]";
	}

	public String getHeadming() {
		return headming;
	}
	
	public void setHeadming(String headming) {
		this.headming = headming;
	}

	public Comment() {
		super();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created == null ? null : created.trim();
    }
}