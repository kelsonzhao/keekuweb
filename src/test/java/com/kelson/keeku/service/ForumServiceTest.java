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
package com.kelson.keeku.service;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import com.kelson.keeku.BaseTest;
import com.kelson.keeku.domain.Forum;
import com.kelson.keeku.domain.Post;
import com.kelson.keeku.domain.Thread;
import com.kelson.keeku.domain.ThreadStateEnum;
import com.kelson.keeku.domain.User;
import com.kelson.keeku.repository.ForumRepository;
import com.kelson.keeku.repository.PostRepository;
import com.kelson.keeku.repository.ThreadRepository;
import com.kelson.utils.BooleanEnum;

public class ForumServiceTest extends BaseTest {
	
	@Autowired
	UserService us;
	
	@Autowired
	ForumService fs;
	
	@Autowired
	ForumRepository fr;
	
	@Autowired
	ThreadRepository tr;
	
	@Autowired
	PostRepository pr;
	
	
	@Before
	public void init() {
		setSecurity("kelsonzhao", "123456");
	}
	@Test
	public void getPostCountInThread() {
		logger.info("f name : " + pr.getPostCountInThread(1));
	}
	@Test
	@Rollback(false)
	public void updateThreadReplies() {
		tr.updateThreadReplies(1, new Date(), (Integer)subject.getSession().getAttribute("userId"),22);
	}
	@Test
	public void findThread(){
		Thread t = tr.findOne(1);
		logger.info("f name : " + t.getForum().getName());
	}
	
	@Test
	@Rollback(false)
	public void editThread(){
		fs.eidtThread(1, 1, "KelsonTest#", "tttddd");
	}
	
	@Test
	@Rollback(false)
	public void createForum(){//创建论坛
		Forum f = new Forum();
		f.setType(0);
		f.setIcon("icon");
		f.setName("中国财经");
		f.setDescription("中国财经讨论");
		f.setDisplayOrder(1);
		f.setModerator("KelsonZhao");
		f.setStyleId(1);
		f.setThreads(0);
		f.setPosts(0);
		
		fr.save(f);
	}
	@Test
	@Rollback(false)
	public void createThread(){//创建主题(包括第一贴)
		Integer forumId = 2;
		String subjectStr = "第2个贴";
		Thread t = new Thread() ;
		t.setForumId(forumId);
		t.setSubject(subjectStr);
		//t.setAuthorUserName((String)subject.getPrincipal());
		t.setAuthorUserId((Integer)subject.getSession().getAttribute("userId"));
		t.setCreatedDate(new Date());
		t.setLastUpdatedDate(new Date());
		//t.setLastUpdatedBy((String)subject.getPrincipal());
		t.setLastUpdatedByUserId((Integer)subject.getSession().getAttribute("userId"));
		t.setViews(1);
		t.setReplies(1);
		t.setDisplayOrder(1);
		t.setDigest(1);
		t.setAttachment(1);
		t.setState(0);
		tr.save(t);
		createPost1(forumId,t.getThreadId(),subjectStr);
	}

	public void createPost1(Integer forumId,Integer threadId, String subjet){//回贴
		//发起新贴子,楼主，1，2，3，
		Post p = new Post();
		p.setForumId(forumId);
		p.setThreadId(threadId);
		p.setSubject(subjet);
		//p.setAuthorUserName((String)subject.getPrincipal());
		p.setAuthorUserId((Integer)subject.getSession().getAttribute("userId"));
		p.setCreatedDate(new Date());
		p.setLastUpdatedDate(new Date());
		//p.setLastUpdatedBy((String)subject.getPrincipal());
		p.setLastUpdatedByUserId((Integer)subject.getSession().getAttribute("userId"));
		p.setAuthorIp("101.202.3.5");
		p.setFloor(1);
		p.setMessage("<p>abc</p>");
		pr.save(p);
	}
	@Test
	@Rollback(false)
	public void createPost(){//回贴
		//发起新贴子,楼主，1，2，3，
		Integer forumId =2;
		Integer threadId =3; 
		String subjetStr = "test";
		Post p = new Post();
		p.setForumId(forumId);
		p.setThreadId(threadId);
		p.setSubject(subjetStr);
		//p.setAuthorUserName((String)subject.getPrincipal());
		p.setAuthorUserId((Integer)subject.getSession().getAttribute("userId"));
		p.setCreatedDate(new Date());
		p.setLastUpdatedDate(new Date());
		//p.setLastUpdatedBy((String)subject.getPrincipal());
		p.setLastUpdatedByUserId((Integer)subject.getSession().getAttribute("userId"));
		p.setAuthorIp("101.202.3.5");
		p.setFloor(1);
		p.setMessage("<p>abc</p>");
		pr.save(p);
	}
	@Test
	public void testPostPage(){
		PageRequest pr2  = new PageRequest(0, 10);
		Page<Post> r = pr.findAll( 1,pr2);
		logger.info("*****************ret:" + r.getNumberOfElements());
		Assert.notNull(r.getContent().get(0).getAuthor());
	}
	@Test
	public void testPage(){
		PageRequest pr  = new PageRequest(0, 10);
		Page<Thread> result = tr.findAll(pr);
		logger.info("*****************ret:" + result.getNumberOfElements());
	}
	@Test
	public void testThreadPage(){
		PageRequest pr  = new PageRequest(0, 10);
		Page<Thread> result = tr.findByForumId(2, pr);
		logger.info("*****************ret:" + result.getNumberOfElements());
		Assert.isTrue(result.getNumberOfElements()>0);
	}
	@Test
	public void test1() {
		logger.info("***************user:" + subject.getPrincipal());
		User user = us.getUser((String)subject.getPrincipal());
		logger.info("**************user:"+user.getEmail());
	}
	@Test
	@Rollback(value=false)
	public void testAddPost(){
		//logger.info("**************floor:"+pr.findMaxFloor(1));
		Integer forumId =2;
		Integer threadId =1; 
		//String subjetStr = "test";
		Post p = new Post();
		p.setForumId(forumId);
		p.setThreadId(threadId);
		//p.setSubject(subjetStr);
		//p.setAuthorUserName((String)subject.getPrincipal());
		p.setAuthorUserId((Integer)subject.getSession().getAttribute("userId"));
		p.setCreatedDate(new Date());
		p.setLastUpdatedDate(new Date());
		//p.setLastUpdatedBy((String)subject.getPrincipal());
		p.setLastUpdatedByUserId((Integer)subject.getSession().getAttribute("userId"));
		p.setAuthorIp("101.202.3.5");
		//p.setFloor(1);
		p.setMessage("<p>abdddddc</p>");
		fs.addPost(p);
	}
	@Test
	@Rollback(value=true)
	public void testAddNewThread(){
		fs.addThread(buildNewThread(2,"junit test 1","<p>ddq</p>"));
	}
	 public Thread buildNewThread(Integer forumId,String title,String content){
	    	//Thread
	    	Thread t = new Thread();
	    	t.setForumId(forumId);
	    	t.setSubject(title);
	    	t.setCreatedDate(new Date());
	    	t.setLastUpdatedDate(new Date());
	    	t.setAuthorUserId(1);
	    	t.setLastUpdatedByUserId(1);
	    	t.setState(ThreadStateEnum.NORMAL.ordinal());
	    	t.setDigest(BooleanEnum.FALSE.ordinal());
	    	t.setViews(0);
	    	t.setReplies(0);
	    	//Post
	    	Post p = new Post();
	    	p.setForumId(forumId);
	    	p.setSubject(title);
	    	p.setCreatedDate(new Date());
	    	p.setLastUpdatedDate(new Date());
	    	p.setAuthorUserId(1);
	    	p.setAuthorIp("102.3332.33.22");
	    	p.setLastUpdatedByUserId(1);
	    	p.setMessage(content);
	    	p.setFloor(0);
	    	
	    	t.setPost(p);
	    	return t;
	    }

}
