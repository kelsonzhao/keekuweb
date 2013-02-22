package com.kelson.keeku.repository;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import com.kelson.keeku.BaseTest;
import com.kelson.keeku.domain.User;

public class UserTest extends BaseTest {
	
	@Autowired
	UserRepository ur;
	
	@Test
	@Rollback(false)
	public void addUser() {
		User user = new User();
		user.setAvatarHeight(67);
		user.setAvatarIcon("c://a.jpg");
		user.setAvatarWidth(67);
		user.setBirthday(new Date());
		user.setCredit(100);
		user.setEmail("kelson.3k@gmail.com");
		user.setGender(0);
		user.setLastActivity(new Date());
		user.setLastIp("10.22.10.30");
		user.setLastVisit(new Date());
		user.setNewMessageCount(100);
		user.setPostCount(200);
		user.setPostPerPage(10);
		user.setQq("100819821");
		user.setRegDate(new Date());
		user.setSecurityQuestion("Hello world.");
		user.setSecurityQuestionA("Hi");
		user.setSelfIntroduction("DDk I'm mad");
		user.setShowEmail(1);
		user.setSignature("KelsonZhao");
		user.setSite("http://www.keeku.co");
		user.setStyleId(2);
		user.setTopicPerPage(20);
		user.setUserName("KelsonZhao");
		user.setPassword("123456");
		user.setWeibo_sina("http://weibo.com/kelson");
		ur.save(user);
	}
	@Test
	public void getAllUser() {
		List<User> ret = ur.findAll();
		Assert.notEmpty(ret);
	}
	
	@Test
	public void test() {
		System.out.println("ABC");
	}

}
