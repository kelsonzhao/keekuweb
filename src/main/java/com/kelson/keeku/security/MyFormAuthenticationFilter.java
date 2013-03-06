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
package com.kelson.keeku.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kelson.logger.LoggerUtil;
import com.kelson.logger.Operation;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		String username = getUsername(request);
		String password = getPassword(request);
		boolean isAjaxLogin = StringUtils.equals(WebUtils.getCleanParam(request, "ajaxLogin"),"1");
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe, host);

		try {
			Subject subject = getSubject(request, response);
			subject.login(token);
   		 	Session session = subject.getSession();
   		 	Integer userId = (Integer)session.getAttribute("userId");
   		 	LoggerUtil.operation(Operation.Login, String.valueOf(userId) +"has logined",(HttpServletRequest) request);
			if(isAjaxLogin){
				if(StringUtils.equals(WebUtils.getCleanParam(request, "needRedirect"),"1")) {//when login successfully by ajax login and redirect to backurl
					SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
					if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
			            request.setAttribute("backUrl", savedRequest.getRequestUrl());
			        }
				}
				return true;
			}else {
				return onLoginSuccess(token, subject, request, response);
			}
		} catch (AuthenticationException e) {
			if(SecurityUtils.getSubject().getSession(false) != null){
				SecurityUtils.getSubject().getSession(false).removeAttribute("userId");
			}
			return onLoginFailure(token, e, request, response);
		}
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		 if (isLoginRequest(request, response) || pathsMatch("/login", request)) {
	            if (isLoginSubmission(request, response)) {
	                if (logger.isTraceEnabled()) {
	                    logger.trace("Login submission detected.  Attempting to execute login.");
	                }
	                return executeLogin(request, response);
	            } else {
	                if (logger.isTraceEnabled()) {
	                    logger.trace("Login page view.");
	                }
	                //allow them to see the login page ;)
	                return true;
	            }
		 }else {
			  if (logger.isTraceEnabled()) {
				  logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
	                        "Authentication url [" + getLoginUrl() + "]");
	            }
	            saveRequestAndRedirectToLogin(request, response);
	            return false;
		 }
	}

	
}
