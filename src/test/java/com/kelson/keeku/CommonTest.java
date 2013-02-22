package com.kelson.keeku;

import java.security.Key;

import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class CommonTest {

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
