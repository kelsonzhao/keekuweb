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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_forum")
public class Forum implements Serializable {

	private static final long serialVersionUID = -3287731647116825352L;
	
	/**
	 * 论坛版块ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer forumId; 
	
	/**
	 * 论坛版块父ID
	 */
	private Integer forumParentId;
	/**
	 * 论坛类型
	 */
	private Integer type;
	/**
	 * 论坛图标
	 */
	private String icon;
	/**
	 * 论坛版块名字
	 */
	private String name;
	/**
	 * 论坛版块描述
	 */
	private String description;
	/**
	 * 论坛版块显示顺序
	 */
	private Integer displayOrder;
	/**
	 * 论坛版块版主
	 */
	private String moderator;
	/**
	 * 样式ID
	 */
	private Integer styleId;
	/**
	 * 论坛版块主题数
	 */
	private Integer threads;
	/**
	 * 论坛版块贴子数
	 */
	private Integer posts;
	
	public Integer getForumId() {
		return forumId;
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}
	public Integer getForumParentId() {
		return forumParentId;
	}
	public void setForumParentId(Integer forumParentId) {
		this.forumParentId = forumParentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getModerator() {
		return moderator;
	}
	public void setModerator(String moderator) {
		this.moderator = moderator;
	}
	public Integer getStyleId() {
		return styleId;
	}
	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}
	public Integer getThreads() {
		return threads;
	}
	public void setThreads(Integer threads) {
		this.threads = threads;
	}
	public Integer getPosts() {
		return posts;
	}
	public void setPosts(Integer posts) {
		this.posts = posts;
	}
	
	

}
