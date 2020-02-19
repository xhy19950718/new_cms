package com.xiehongyuan.pojo;

import java.io.Serializable;

public class Tousu  implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(这个变量表示�?�?)   
	 */  
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer articleid;

    private Integer userid;
    
    private Integer tousuType;
    
    private String tousucontent;

    private String created;
    


    

	@Override
	public String toString() {
		return "Tousu [id=" + id + ", articleid=" + articleid + ", userid=" + userid + ", tousucontent=" + tousucontent
				+ ", created=" + created +"tousuType="+tousuType+ "]";
	}


	
	
	public Integer getTousuType() {
		return tousuType;
	}




	public void setTousuType(Integer tousuType) {
		this.tousuType = tousuType;
	}




	public Tousu() {
		super();
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


    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created == null ? null : created.trim();
    }

	public String getTousucontent() {
		return tousucontent;
	}

	public void setTousucontent(String tousucontent) {
		this.tousucontent = tousucontent;
	}
}