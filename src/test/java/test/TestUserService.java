package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.mb.application.UserService;
import cn.fyg.mb.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestUserService {
	
	@Autowired
	UserService userService;
	
	@Test
	public void testSave(){
		User user = new User();
		user.setKey_("test4");
		user.setRealname("realname4");
		this.userService.save(user);
		
		this.userService.delete(user.getKey_());
	}

}
