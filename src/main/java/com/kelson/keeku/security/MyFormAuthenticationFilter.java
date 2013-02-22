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

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		String username = getUsername(request);
		String password = getPassword(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe, host);

		try {
			Subject subject = getSubject(request, response);
			subject.login(token);
			if(StringUtils.isBlank(WebUtils.getCleanParam(request, "NeedSuccessRedirect"))){
				return true;
			}else {
				return onLoginSuccess(token, subject, request, response);
			}
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}

}
