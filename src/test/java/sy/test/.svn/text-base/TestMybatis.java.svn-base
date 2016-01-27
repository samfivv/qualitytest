package sy.test;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.midai.miya.sys.model.Log;
import com.midai.miya.sys.service.LogService;
import com.midai.miya.user.model.User;
import com.midai.miya.user.service.UserService;
import com.midai.miya.utils.UUIDUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestMybatis {

	private static final Logger logger = Logger.getLogger(TestMybatis.class);

	private UserService userService;
	@Autowired
	private LogService logService;
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Test
	public void test1() {
		User u = userService.getUserById("1");
		logger.info(JSON.toJSON(u));
	}

	
	

}
