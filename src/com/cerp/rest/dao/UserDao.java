package com.cerp.rest.dao;

import com.cerp.rest.model.User;

public interface UserDao {

	public String addUser(User user);

	public String modifyUser(User user);

	public String blockUser(User user);

	public int verifyUser(String username);

	public int changePwd(User user);

}
