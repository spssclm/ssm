package com.cloudcross.anon.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloudcross.anon.dao.IUserDao;
import com.cloudcross.anon.model.User;
import com.cloudcross.anon.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}

}
