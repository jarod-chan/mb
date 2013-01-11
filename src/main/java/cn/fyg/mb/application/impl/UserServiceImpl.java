package cn.fyg.mb.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.mb.application.UserService;
import cn.fyg.mb.domain.User;
import cn.fyg.mb.domain.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	@Transactional
	public User save(User user) {
		this.userMapper.save(user);
		return userMapper.find(user.getKey_());
	}

	@Override
	@Transactional
	public void delete(String key_) {
		this.userMapper.delete(key_);
	}

}
