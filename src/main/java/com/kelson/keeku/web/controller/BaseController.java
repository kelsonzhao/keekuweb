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
package com.kelson.keeku.web.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;

public class BaseController {
	
	public void putUserInfo(Map<String,Object> model) {
		Subject s = SecurityUtils.getSubject();
		Object userName = s.getPrincipal();
		if(userName != null) {
			model.put("username", userName);
		}
	}
	public void putUserInfo(Model model) {
		Subject s = SecurityUtils.getSubject();
		Object userName = s.getPrincipal();
		if(userName != null) {
			model.addAttribute("username", userName);
		}
	}
	

}
