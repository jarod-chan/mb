package cn.fyg.mb.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloCtl {
	
	public static final Logger logger=LoggerFactory.getLogger(HelloCtl.class);
	
	private static final String PATH = "";
	private interface Page {
		String HELLO = PATH + "hello";
	}
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String toHello(){
		logger.info("toHello");
		return Page.HELLO;
	}

}
