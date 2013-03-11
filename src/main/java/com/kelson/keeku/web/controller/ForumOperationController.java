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
package com.kelson.keeku.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kelson.keeku.domain.Forum;
import com.kelson.keeku.domain.ForumBuilder;
import com.kelson.keeku.domain.Post;
import com.kelson.keeku.domain.Thread;
import com.kelson.keeku.service.ForumService;

@Controller
@RequestMapping(value = "forumOpr/")
public class ForumOperationController extends BaseController {

	@Autowired
	ForumService fs;


	@RequestMapping(value = "{forumId}/newthreadView", method = RequestMethod.GET)
	public ModelAndView toNewThreadView(@PathVariable("forumId") Integer forumId,Model model) {
		putUserInfo(model);
		Forum f = fs.getForum(forumId);
		model.addAttribute("forumId",forumId);
		model.addAttribute("forumName",f.getName());
		return new ModelAndView("newThreadView",model.asMap());
	}
	@RequestMapping(value = "{forumId}/newthread", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> newThread(@PathVariable("forumId") Integer forumId,@Param("title") String title,@Param("content") String content,Model model,HttpServletRequest request) {
		Map<String,Object> ret = new HashMap<String,Object>();
		try{
			Thread t = fs.addThread(ForumBuilder.buildNewThread(forumId, title, content, this.getCurrentUserId(),this.getIp(request)));
			ret.put("threadId", t.getThreadId());
			ret.put("success", 1);
		}catch(Exception e) {
			ret.put("success", 0);
			ret.put("message",e);
		}
		return ret;
	}
	@RequestMapping(value = "{threadId}/{postId}/editThreadView", method = RequestMethod.GET)
	public ModelAndView toEditThreadView(@PathVariable("threadId") Integer threadId,@PathVariable("postId") Integer postId,Model model) {
		putUserInfo(model);
		Post p = fs.getPost(postId);
		model.addAttribute("threadId",threadId);
		model.addAttribute("postId",postId);
		model.addAttribute("updateFlag","1");//update thread
		model.addAttribute("title",p.getSubject());//update thread
		model.addAttribute("content",p.getMessage());//update thread
		return new ModelAndView("newThreadView",model.asMap());
	}
	@RequestMapping(value = "{threadId}/{postId}/editPostView", method = RequestMethod.GET)
	public ModelAndView toEditPostView(@PathVariable("threadId") Integer threadId,@PathVariable("postId") Integer postId,Model model) {
		putUserInfo(model);
		Thread t = fs.getThread(threadId);
		Post p = fs.getPost(postId);
		model.addAttribute("threadId",threadId);
		model.addAttribute("subject",t.getSubject());
		model.addAttribute("postId",postId);
		model.addAttribute("updateFlag","2");//update post
		model.addAttribute("content",p.getMessage());//update thread
		return new ModelAndView("newThreadView",model.asMap());
	}
	@RequestMapping(value = "{threadId}/{postId}/editThread", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> editThread(@PathVariable("threadId") Integer threadId,@PathVariable("postId") Integer postId,@Param("title") String title,@Param("content") String content,Model model,HttpServletRequest request) {
		Map<String,Object> ret = new HashMap<String,Object>();
		try{
			fs.eidtThread(threadId, postId, title, content);
			ret.put("success", 1);
		}catch(Exception e) {
			ret.put("success", 0);
			ret.put("message",e);
		}
		return ret;
	}
	@RequestMapping(value = "{threadId}/{postId}/editPost", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> editPost(@PathVariable("threadId") Integer threadId,@PathVariable("postId") Integer postId,@Param("content") String content,Model model,HttpServletRequest request) {
		Map<String,Object> ret = new HashMap<String,Object>();
		try{
			fs.eidtPost(threadId, postId, content);
			ret.put("success", 1);
		}catch(Exception e) {
			ret.put("success", 0);
			ret.put("message",e);
		}
		return ret;
	}
	@RequestMapping(value = "{threadId}/createPostView", method = RequestMethod.GET)
	public ModelAndView toCreatePostView(@PathVariable("threadId") Integer threadId,Model model) {
		putUserInfo(model);
		Thread t = fs.getThread(threadId);
		model.addAttribute("forumId",t.getForumId());
		model.addAttribute("forumName",t.getForum().getName());
		model.addAttribute("threadId",threadId);
		model.addAttribute("subject",t.getSubject());
		model.addAttribute("updateFlag","3");//create post
		return new ModelAndView("newThreadView",model.asMap());
	}
	@RequestMapping(value = "{threadId}/createPost", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> createPost(@PathVariable("threadId") Integer threadId,@Param("content") String content,Model model,HttpServletRequest request) {
		Map<String,Object> ret = new HashMap<String,Object>();
		try{
			fs.createPost(threadId, content, this.getCurrentUserId(), this.getIp(request));
			ret.put("success", 1);
		}catch(Exception e) {
			ret.put("success", 0);
			ret.put("message",e);
		}
		return ret;
	}

}
