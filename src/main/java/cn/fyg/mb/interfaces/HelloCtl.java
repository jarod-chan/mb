package cn.fyg.mb.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.mb.domain.UserMapper;

@Controller
public class HelloCtl {
	
	public static final Logger logger=LoggerFactory.getLogger(HelloCtl.class);
	
	private static final String PATH = "";
	private interface Page {
		String HELLO = PATH + "hello";
	}
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String toHello(Map<String,Object> map){
		logger.info("toHello");
		List<HashMap<String, Object>> userMap = userMapper.getAll();
		map.put("userMap", userMap);
		for(HashMap<String,Object> objMap:userMap){
			System.out.println(objMap.get("uuid"));
			System.out.println(objMap.get("realname"));
		}
		return Page.HELLO;
	}

}
