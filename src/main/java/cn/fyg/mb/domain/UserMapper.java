package cn.fyg.mb.domain;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
	
	public List<HashMap<String,Object>> getAll();
	
	public List<HashMap<String,Object>> getList();
}
