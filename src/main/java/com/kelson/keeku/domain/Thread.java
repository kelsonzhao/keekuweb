/*
 * Copyright 2010~2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kelson.keeku.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
* @ClassName: Thread 
* @Description: 论坛主题
* @author Kelson 
* @date 2013-2-21 下午8:46:43 
* @version 1.0
 */
@Entity
@Table(name="t_thread")
public class Thread implements Serializable {

	private static final long serialVersionUID = -4505368435346192777L;
	
	/**
	 * 主题ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer threadId;
	/**
	 * 所属论坛版块
	 */
	private Integer forumId;
	
	private String subject;
	
	private Integer authorUserId;
	
	private Date createdDate;
	
	private Date lastUpdatedDate;
	
	private Integer lastUpdatedByUserId;
	
	private Integer views;
	
	private Integer replies;
	
	private Integer displayOrder;
	
	private Integer digest;
	
	private Integer attachment;
	
	private Integer state;
	
	/**
	 * 与主题对应的一楼
	 */
	@Transient
	private Post post;

	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Integer getThreadId() {
		return threadId;
	}

	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}

	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}



	public Integer getAuthorUserId() {
		return authorUserId;
	}

	public void setAuthorUserId(Integer authorUserId) {
		this.authorUserId = authorUserId;
	}


	public Integer getLastUpdatedByUserId() {
		return lastUpdatedByUserId;
	}

	public void setLastUpdatedByUserId(Integer lastUpdatedByUserId) {
		this.lastUpdatedByUserId = lastUpdatedByUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getReplies() {
		return replies;
	}

	public void setReplies(Integer replies) {
		this.replies = replies;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getDigest() {
		return digest;
	}

	public void setDigest(Integer digest) {
		this.digest = digest;
	}

	public Integer getAttachment() {
		return attachment;
	}

	public void setAttachment(Integer attachment) {
		this.attachment = attachment;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	

}
