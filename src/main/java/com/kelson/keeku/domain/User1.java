package com.kelson.keeku.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 
 * @ClassName: User
 * @Description: 用户领域对象
 * @author Kelson
 * @date 2013-1-27 上午1:21:35
 * @version 1.0
 */
@Entity
@Table(name = "t_user")
public class User1 implements Serializable {

	private static final long serialVersionUID = 7189752172263221627L;

	@Id
	private Integer userId;

	/* private String password; */

	private String userName;

	/*
	 * private String securityQuestion;
	 * 
	 * private String securityQuestionA;
	 */

	private Integer gender;

	private Date regDate;

	/*
	 * private String lastIp;
	 * 
	 * private Date lastVisit;
	 * 
	 * private Date lastActivity;
	 */

	private Integer postCount;

	private Integer replyCount;

	private Integer credit;

	/*
	 * private String email;
	 * 
	 * private String site;
	 * 
	 * private String qq;
	 */

	/*
	 * private Date birthday;
	 * 
	 * private String weibo_sina;
	 */
	private String selfIntroduction;

	private String avatarIcon;

	private Integer avatarWidth;

	private Integer avatarHeight;

	private String signature;

	private Integer topicPerPage;

	private Integer postPerPage;

	private Integer styleId;

	/* private Integer showEmail; */

	private Integer newMessageCount;

	public Integer getUserId() {
		return userId;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Integer getPostCount() {
		return postCount;
	}

	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getSelfIntroduction() {
		return selfIntroduction;
	}

	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}

	public String getAvatarIcon() {
		return avatarIcon;
	}

	public void setAvatarIcon(String avatarIcon) {
		this.avatarIcon = avatarIcon;
	}

	public Integer getAvatarWidth() {
		return avatarWidth;
	}

	public void setAvatarWidth(Integer avatarWidth) {
		this.avatarWidth = avatarWidth;
	}

	public Integer getAvatarHeight() {
		return avatarHeight;
	}

	public void setAvatarHeight(Integer avatarHeight) {
		this.avatarHeight = avatarHeight;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getTopicPerPage() {
		return topicPerPage;
	}

	public void setTopicPerPage(Integer topicPerPage) {
		this.topicPerPage = topicPerPage;
	}

	public Integer getPostPerPage() {
		return postPerPage;
	}

	public void setPostPerPage(Integer postPerPage) {
		this.postPerPage = postPerPage;
	}

	public Integer getStyleId() {
		return styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	public Integer getNewMessageCount() {
		return newMessageCount;
	}

	public void setNewMessageCount(Integer newMessageCount) {
		this.newMessageCount = newMessageCount;
	}

}
