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
package com.kelson.keeku.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kelson.keeku.domain.Forum;
import com.kelson.keeku.domain.Post;
import com.kelson.keeku.domain.Thread;

public interface ForumService {
	
	public List<Forum> listForums();
	
	public Thread addThread(Thread t) ;
	
	public int eidtThread(Thread t);
	
	public int eidtThread(Integer threadId,Integer postId,String subject,String message);
	
	public int eidtPost(Integer threadId,Integer postId,String message);
	
	public Post createPost(Integer threadId, String message,Integer userId,String Ip);

	public Page<Thread> listThreads(Pageable pageable);
	
	public Page<Thread> listThreads(Pageable pageable,Integer forumId);

	public Page<Post> listPosts(Pageable pageable, Integer threadId);

	public Post addPost(Post p);
	
	public Post getPost(Integer postId);
	
	public Thread getThread(Integer threadId);
	
	public Forum getForum(Integer forumId);
	
	public int getThreadPostTotalPage(Integer threadId,int size);

}
