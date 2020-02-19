package com.xiehongyuan.pojo;

import java.io.Serializable;

public class Category  implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(这个变量表示�?�?)   
	 */  
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private Integer channelId;
    
    @Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", channelId=" + channelId + "]";
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}