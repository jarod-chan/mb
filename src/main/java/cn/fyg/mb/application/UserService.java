package cn.fyg.mb.application;

import cn.fyg.mb.domain.User;

public interface UserService {
	
	User save(User user);
	
	void delete(String key_);

}
