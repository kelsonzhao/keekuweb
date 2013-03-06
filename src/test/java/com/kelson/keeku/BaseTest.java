package com.kelson.keeku;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.kelson.keeku.service.UserService;

@ContextConfiguration(locations = {"classpath:spring/app-context.xml","classpath:spring/shiro-app-context.xml"})
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("defaultSecurityManager")
	public DefaultSecurityManager dsm;
	
	public Subject subject;
	
	public void setSecurity(String username,String password){
		//subject = SecurityUtils.getSubject();
		SecurityUtils.setSecurityManager(dsm);
		UsernamePasswordToken token = new UsernamePasswordToken(username,password, false);
		//dsm.authenticate(token);
		subject = SecurityUtils.getSubject();
		subject.login(token);
	}
}
