package cn.fyg.mb.domain;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserMapper {
	
	public List<HashMap<String,Object>> getAll();
	
	@Transactional
	public void save(User user);
	
	public User find(String key_);
	
	@Transactional
	public void delete(String key_);
}
