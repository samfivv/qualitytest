package com.midai.miya.user.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.user.model.User;
import com.midai.miya.user.model.UserDay;
import com.midai.miya.utils.PageUtil;


public interface UserDao {
	
	User selectByPrimaryKey(String id);
	
	int getUserCount(@Param("user")User user);
	
	List<User> getUserByCondition(@Param("user")User user,@Param("pageUtil") PageUtil pageUtil);
	
	void update(@Param("user")User user);
	
	/**
	 * 新注册用户图表(按天)
	 * 王梦圆
	 * 2015年5月5日
	 */
	List<UserDay> findCountByDay(@Param("queryBeginRegisterTimeStr")String queryBeginRegisterTimeStr,
			@Param("queryEndRegisterTimeStr")String queryEndRegisterTimeStr);
	/**
	 * 新注册用户图表(按小时)
	 * 王梦圆
	 * 2015年5月25日
	 */
	List<UserDay> findCountByHour(@Param("queryBeginRegisterTimeStr")String queryBeginRegisterTimeStr,
			@Param("queryEndRegisterTimeStr")String queryEndRegisterTimeStr);
	
	/**
	 * 查询一星期之内未登录的用户
	 * 彭坤
	 * 2015年4月30日
	 */
	List<User> findErrorUser(@Param("user")User user,@Param("pageUtil") PageUtil pageUtil);
	
	/**
	 * 查询异常会员个数
	 * 王梦圆
	 * 2015年5月8日
	 */
	long findCountErrorUser(@Param("user")User user);
	
}