
package com.kelson.keeku.service;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kelson.keeku.BaseTest;
import com.kelson.keeku.domain.User;

public class UserServiceTest extends BaseTest {
	
	@Autowired
	UserService us;
	
	@Test
	public void getUser(){
		User user = us.getUser("KelsonZhao");
		Assert.assertNotNull(user);
	}
	@Test
	public void login() {
		
	}
}
