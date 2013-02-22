package com.kelson.keeku.service;

import com.kelson.keeku.domain.User;

public interface UserService {
	
	public User addUser(User user);
	
	public User getUser(String userName);

}
