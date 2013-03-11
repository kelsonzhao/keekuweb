/*
 * Copyright 2010~2013 keeku.co.
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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "t_post")
public class Post implements Serializable {

	private static final long serialVersionUID = -5100494243098887952L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;

	private Integer forumId;

	private Integer threadId;

	private String subject;

	private Integer authorUserId;

	private Date createdDate;

	private Date lastUpdatedDate;

	private Integer lastUpdatedByUserId;

	private String authorIp;

	private String message;

	private Integer floor;

	@ManyToOne(targetEntity = User1.class)  // default  fetch type for OneToOne is eager
    @JoinColumn(name="authorUserId",insertable=false,updatable=false)
	@Fetch(FetchMode.JOIN)
	private User1 author;
	
	private int isModified;

	
	public int getIsModified() {
		return isModified;
	}

	public void setIsModified(int isModified) {
		this.isModified = isModified;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public User1 getAuthor() {
		return author;
	}

	public void setAuthor(User1 author) {
		this.author = author;
	}

	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	public Integer getThreadId() {
		return threadId;
	}

	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
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

	public String getAuthorIp() {
		return authorIp;
	}

	public void setAuthorIp(String authorIp) {
		this.authorIp = authorIp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

}
