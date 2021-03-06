package com.xiehongyuan.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zhanggm.common.utils.DateUtil;

public class User implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(这个变量表示�?�?)   
	 */  
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String username;

    private String password;

    private String nickname;

    private Date birthday;

    private Integer gender;

    private Integer locked;

    private int score;

    private String role;

    private String url;

    private Date createTime;

    private Date updateTime;
   
    private String birthdayStr;
    
    private String headming;
    
    private String isMima;
    
    
    public String getIsMima() {
		return isMima;
	}

	public void setIsMima(String isMima) {
		this.isMima = isMima;
	}

	public boolean isAdmin() {
    	return "1".equals(getRole());
    }
    
    public String getHeadming() {
		return headming;
	}

	public void setHeadming(String headming) {
		this.headming = headming;
	}
    
    public String getBirthdayStr() {
    	if(this.getBirthday()==null) {
    		return null;
    	}
    	 SimpleDateFormat sFormat=new SimpleDateFormat("yyyy-MM-dd");
        return sFormat.format(this.getBirthday());
    }

    @Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", birthday=" + birthday + ", gender=" + gender + ", locked=" + locked + ", score=" + score
				+ ", role=" + role + ", url=" + url + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Date getBirthday() {
        return birthday;
    }
    
    

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    

    public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}