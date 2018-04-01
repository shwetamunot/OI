package com.tcs.dao;

import com.tcs.model.Users;

public interface UserDAO {
	void register(Users user);
	boolean isEmailValid(String email);
	boolean isUsernameValid(String username);
	Users login(Users user);
	void updateUser(Users user);
	Users getUserByUsername(String username);

}
