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
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kelson.keeku.domain.Post;
import com.kelson.keeku.domain.Thread;
import com.kelson.keeku.service.ForumService;

@Controller
@RequestMapping(value = "forum")
public class ForumController extends BaseController {

	@Autowired
	ForumService fs;
	
	@RequestMapping(value = "viewForumsList", method = RequestMethod.GET)
	public ModelAndView toListForumView(Locale locale, Model model) {
		putUserInfo(model);
		model.addAttribute("forums",fs.listForums());
		return new ModelAndView("forumsListView",model.asMap());
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listForum() {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("data", fs.listForums());
		return ret;
	}

	@RequestMapping(value = "{forumId}/viewThreadsList", method = RequestMethod.GET)
	public ModelAndView toForumView(@PathVariable("forumId") Integer forumId, Model model) {
		putUserInfo(model);
		model.addAttribute("forumId",forumId);
		return new ModelAndView("forumThreadsListView",model.asMap());
	}
	
	@RequestMapping(value = "thread/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listTheads() {
		Map<String, Object> ret = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(0, 10);
		Page<Thread> result = fs.listThreads(pageable);
		ret.put("data", result);
		return ret;
	}

	@RequestMapping(value = "{forumId}/thread/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listForumTheads(@PathVariable("forumId") Integer forumId) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(0, 10);
		Page<Thread> result = fs.listThreads(pageable, forumId);
		ret.put("data", result);
		return ret;
	}

	@RequestMapping(value = "thread/{threadId}/post/list/{pageNum}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listPosts(@PathVariable("threadId") Integer threadId, @PathVariable("pageNum") String pageNum) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Pageable pageable = null;
		int pageSize = this.getDefaultPageSize();
		if("lastpage".equals(pageNum)) {
			pageable = new PageRequest(fs.getThreadPostTotalPage(threadId, pageSize)-1, pageSize);//页码从0开始 
		}else if("firstpage".equals(pageNum)) {
			pageable = new PageRequest(0, pageSize);//页码从0开始 
		}else {
			try{
				pageable = new PageRequest(Integer.valueOf(pageNum), pageSize);//页码从0开始 
			}catch(Exception e) {
				pageable = new PageRequest(0, pageSize);//页码从0开始 
			}
		}
		Page<Post> result = fs.listPosts(pageable, threadId);
		ret.put("data", result);
		return ret;
	}

	@RequestMapping(value = "thread/{threadId}/viewpost/{pageNum}", method = RequestMethod.GET)
	public ModelAndView viewpost(@PathVariable("threadId") Integer threadId, @PathVariable("pageNum") String pageNum,Model model) {
		putUserInfo(model);
		Thread t = fs.getThread(threadId);
		model.addAttribute("forumId", t.getForumId());
		model.addAttribute("threadId", threadId);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", this.getDefaultPageSize());
		return new ModelAndView("threadPostsView", model.asMap());
	}
	@RequestMapping(value = "thread/{threadId}/viewpost", method = RequestMethod.GET)
	public ModelAndView viewpostInPage(@PathVariable("threadId") Integer threadId,Model model) {
		putUserInfo(model);
		Thread t = fs.getThread(threadId);
		model.addAttribute("forumId", t.getForumId());
		model.addAttribute("threadId", threadId);
		model.addAttribute("pageNum", this.getDefaultPageNum());
		model.addAttribute("pageSize", this.getDefaultPageSize());
		return new ModelAndView("threadPostsView", model.asMap());
	}
	

}
