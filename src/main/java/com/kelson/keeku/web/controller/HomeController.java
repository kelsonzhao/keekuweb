package com.kelson.keeku.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController{
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		putUserInfo(model);
		return new ModelAndView("home",model.asMap());
	}
	
	@RequestMapping(value = "/jqplot_test", method = RequestMethod.GET)
	public ModelAndView toJqplotTest(Locale locale, Model model) {
		putUserInfo(model);
		return new ModelAndView("jqplot_test",model.asMap());
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(HttpServletRequest request){
		Map<String,Object> ret  = new HashMap<String,Object>();
		//String msg = request.getParameter("shiroLoginFailure");
		String msg = (String)request.getAttribute("shiroLoginFailure");
		String backUrl = (String)request.getAttribute("backUrl");
		putUserInfo(ret);
		if(!StringUtils.isBlank(msg)){
			ret.put("message", "Wrong username or password!");
			ret.put("success", 0);
		}else {//login successful
			ret.put("success", 1);
			ret.put("needRedirect", WebUtils.getCleanParam(request, "needRedirect"));
			ret.put("backUrl", backUrl);
		}
		return ret;
	}
	@RequestMapping(value = "/login/please")
	public ModelAndView toLoginView(Locale locale, Model model){
		
		return new ModelAndView("loginView",model.asMap());
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> logout(){
		Map<String,Object> ret  = new HashMap<String,Object>();
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		ret.put("message", "Logout successful!");
		return ret;
	}
	
}
