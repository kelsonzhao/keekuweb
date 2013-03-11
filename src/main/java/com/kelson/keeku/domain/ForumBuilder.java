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

import java.util.Date;

import com.kelson.utils.BooleanEnum;

public class ForumBuilder {

	public static Thread buildNewThread(Integer forumId, String title, String content, Integer userId, String ip) {
		// Thread
		Thread t = new Thread();
		t.setForumId(forumId);
		t.setSubject(title);
		t.setCreatedDate(new Date());
		t.setLastUpdatedDate(new Date());
		t.setAuthorUserId(userId);
		t.setLastUpdatedByUserId(userId);
		t.setState(ThreadStateEnum.NORMAL.ordinal());
		t.setDigest(BooleanEnum.FALSE.ordinal());
		t.setViews(0);
		t.setReplies(0);

		// Post
		Post p = new Post();
		p.setForumId(forumId);
		p.setSubject(title);
		p.setCreatedDate(new Date());
		p.setLastUpdatedDate(new Date());
		p.setAuthorUserId(userId);
		p.setAuthorIp(ip);
		p.setLastUpdatedByUserId(userId);
		p.setMessage(content);
		p.setFloor(0);

		t.setPost(p);
		return t;
	}

	public static Post buildNewPost(Integer forumId,Integer threadId,String title,String content,Integer userId,String ip) {
		  Post p = new Post();
	    	p.setForumId(forumId);
	    	p.setThreadId(threadId);
	    	p.setSubject(title);
	    	p.setCreatedDate(new Date());
	    	p.setLastUpdatedDate(new Date());
	    	p.setAuthorUserId(userId);
	    	p.setAuthorIp(ip);
	    	p.setLastUpdatedByUserId(userId);
	    	p.setMessage(content);
	    	p.setFloor(0);
	    	return p;
	  }
}
