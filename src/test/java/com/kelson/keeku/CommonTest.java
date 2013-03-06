package com.kelson.keeku;

import java.security.Key;

import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.PatternMatcher;
import org.joda.time.DateTime;
import org.junit.Test;

public class CommonTest {
	
	@Test
	public void testPatternMatcher() {
		PatternMatcher pm = new AntPathMatcher();
		System.out.println("ret:" + pm.matches("/login/**", "/login/please"));
	}
	
	@Test
	public void testDate() {
		DateTime d = new DateTime();
		System.out.println("***************:" + d.toString("yyyy-MM-dd HH:mm:ss"));
	}

	@Test
	public void test1() {
		AesCipherService cipherService = new AesCipherService();
		cipherService.setKeySize(256);
		// create a test key:
		Key testKey = cipherService.generateNewKey();
		Key[] keies = new Key[1];
		keies[0] = testKey;
		// encrypt a file’s bytes:
		ByteSource ret = cipherService.encrypt("abc".getBytes(), testKey.getEncoded());
		//byte[] encrypted = cipherService.encrypt("abc".getBytes(), keies.);
		
		//String e =new Md5Hash("dsfdsfds").toString();//013f890e35d1b7f5e45f21e60f7863d6
		System.out.println("***************:" + ret.toString());
		
	}

}
