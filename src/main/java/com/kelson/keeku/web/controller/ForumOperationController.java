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

import java.util.Date;
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

import com.kelson.keeku.domain.Post;
import com.kelson.keeku.domain.Thread;
import com.kelson.keeku.domain.ThreadStateEnum;
import com.kelson.keeku.service.ForumService;
import com.kelson.utils.BooleanEnum;

@Controller
@RequestMapping(value = "forumOpr/")
public class ForumOperationController extends BaseController {

	@Autowired
	ForumService fs;


	@RequestMapping(value = "{forumId}/newthreadView", method = RequestMethod.GET)
	public ModelAndView toNewThreadView(@PathVariable("forumId") Integer forumId,Model model) {
		putUserInfo(model);
		model.addAttribute("forumId",forumId);
		return new ModelAndView("newThreadView",model.asMap());
	}
	@RequestMapping(value = "{forumId}/newthread", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> newThread(@PathVariable("forumId") Integer forumId,@Param("title") String title,@Param("content") String content,Model model,HttpServletRequest request) {
		Map<String,Object> ret = new HashMap<String,Object>();
		try{
			fs.addThread(this.buildNewThread(forumId, title, content, request));
			ret.put("success", 1);
		}catch(Exception e) {
			ret.put("success", 0);
			ret.put("message",e);
		}
		return ret;
	}
	@RequestMapping(value = "{threadId}/newthreadView", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> editThread(@PathVariable("threadId") Integer threadId,@Param("title") String title,@Param("content") String content,Model model,HttpServletRequest request) {
		Map<String,Object> ret = new HashMap<String,Object>();
		try{
			//update thread
			ret.put("success", 1);
		}catch(Exception e) {
			ret.put("success", 0);
			ret.put("message",e);
		}
		return ret;
	}
    public Thread buildNewThread(Integer forumId,String title,String content,HttpServletRequest request){
    	//Thread
    	Thread t = new Thread();
    	t.setForumId(forumId);
    	t.setSubject(title);
    	t.setCreatedDate(new Date());
    	t.setLastUpdatedDate(new Date());
    	t.setAuthorUserId(this.getCurrentUserId());
    	t.setLastUpdatedByUserId(this.getCurrentUserId());
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
    	p.setAuthorUserId(this.getCurrentUserId());
    	p.setAuthorIp(this.getIp(request));
    	p.setLastUpdatedByUserId(this.getCurrentUserId());
    	p.setMessage(content);
    	p.setFloor(0);
    	
    	t.setPost(p);
    	return t;
    }

}
