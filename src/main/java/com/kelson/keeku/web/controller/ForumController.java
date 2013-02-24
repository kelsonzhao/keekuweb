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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "thread/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listTheads() {
		Map<String, Object> ret = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(0, 10);
		Page<Thread> result = fs.listThreads(pageable);
		ret.put("data", result);
		return ret;
	}

	@RequestMapping(value = "thread/{threadId}/post/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listPosts(@PathVariable("threadId") Integer threadId, @PathVariable("pageNum") Integer pageNum,
			@PathVariable("pageSize") Integer pageSize) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(pageNum, pageSize);
		Page<Post> result = fs.listPosts(pageable, threadId);
		ret.put("data", result);
		return ret;
	}
	
	@RequestMapping(value = "/thread/{threadId}/viewpost/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public ModelAndView home(@PathVariable("threadId") Integer threadId, @PathVariable("pageNum") Integer pageNum,
			@PathVariable("pageSize") Integer pageSize,Model model) {
		putUserInfo(model);
		model.addAttribute("threadId", threadId);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		return new ModelAndView("postsView",model.asMap());
	}

}
