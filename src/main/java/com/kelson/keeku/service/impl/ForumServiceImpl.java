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
package com.kelson.keeku.service.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kelson.keeku.domain.Forum;
import com.kelson.keeku.domain.Post;
import com.kelson.keeku.domain.Thread;
import com.kelson.keeku.repository.ForumRepository;
import com.kelson.keeku.repository.PostRepository;
import com.kelson.keeku.repository.ThreadRepository;
import com.kelson.keeku.service.ForumService;

@Service
public class ForumServiceImpl implements ForumService{
	
	@Autowired
	ForumRepository fr;
	
	@Autowired
	ThreadRepository tr;
	
	@Autowired
	PostRepository pr;
	
	@Autowired
	EntityManagerFactory emf;
	
	

	@Override
	public List<Forum> listForums() {
		return fr.findAll();
	}

	@Override
	public Thread addThread(Thread t) {
		tr.save(t);
		Post p = t.getPost();
		p.setThreadId(t.getThreadId());
		pr.save(p);
		return t;
	}

	@Override
	public Page<Thread>  listThreads(Pageable pageable) {
		return tr.findAll(pageable);
	}

	@Override
	public Page<Post> listPosts(Pageable pageable, Integer threadId) {
		return pr.findAll(threadId,pageable);
	}

	@Override
	public Post addPost(Post post) {
		post.setFloor(pr.findMaxFloor(post.getThreadId()));
		pr.saveAndFlush(post);
		/*EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query a = em.createNativeQuery("INSERT INTO t_post (postId, forumId, threadId, subject, authorUserName, createdDate, lastUpdatedBy, lastUpdatedDate, message,authorIp, floor) VALUES (3, 2, 1, 'xxxxxx', 'KelsonZhao', '2013-02-23 00:15:12', 'KelsonZhao', '2013-02-23 00:15:12', '<p>abc</p>','123.11',2)");
		a.executeUpdate();
		//t.commit();
		em.close();*/
		return post;
	}

	@Override
	public Page<Thread> listThreads(Pageable pageable, Integer forumId) {
		return tr.findByForumId(forumId, pageable);
	}


}
