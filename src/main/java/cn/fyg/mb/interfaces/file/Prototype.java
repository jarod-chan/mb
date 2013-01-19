package cn.fyg.mb.interfaces.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("prototype")
public class Prototype {
	
	public static final Logger logger=LoggerFactory.getLogger(Prototype.class);
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toUpload(){
		return "file/prototype";
	}

}
