package com.midai.miya.user.service;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.user.model.User;
import com.midai.miya.user.model.UserDay;
import com.midai.miya.utils.PageUtil;
public interface UserService {

	public User getUserById(String id);

	List<User> getUserByCondition(User user,PageUtil pageUtil);
	
	int getUserCount(User user);
	
	void update(User user);
	
	List<UserDay> findCountByDay(String queryBeginRegisterTimeStr,String queryEndRegisterTimeStr);
	
	List<UserDay> findCountByHour(String queryBeginRegisterTimeStr,String queryEndRegisterTimeStr);
	
	List<User> findErrorUser(User user, PageUtil pageUtil);
	
	long findCountErrorUser(User user);
 
}