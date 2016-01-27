package com.midai.miya.user.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.user.dao.UserDao;
import com.midai.miya.user.model.User;
import com.midai.miya.user.model.UserDay;
import com.midai.miya.user.service.UserService;
import com.midai.miya.utils.CalendarUtil;
import com.midai.miya.utils.PageUtil;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	

	@Override
	public User getUserById(String id) {
		return userDao.selectByPrimaryKey(id);
	}
	
	public UserDao getUserMapper() {
		return userDao;
	}

	public void setUserMapper(UserDao userMapper) {
		this.userDao = userMapper;
	}

	@Override
	public List<User> getUserByCondition(User user,PageUtil pageUtil) {
		if(user.getUserMail()!=null){
			user.setUserMail(user.getUserMail().replace(" ", ""));
		}
		List<User> list=userDao.getUserByCondition(user, pageUtil);
		return list;
	}

	@Override
	public int getUserCount(User user) {
		if(user.getUserMail()!=null){
			user.setUserMail(user.getUserMail().replace(" ", ""));
		}
		int count =userDao.getUserCount(user);
		return count;
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		
	}
	
	@Override
	public List<UserDay> findCountByDay(String queryBeginRegisterTimeStr,
			String queryEndRegisterTimeStr) {
		 List<UserDay> userCount=userDao.findCountByDay(queryBeginRegisterTimeStr, queryEndRegisterTimeStr);
		 if(queryBeginRegisterTimeStr.equals("") ){
			 Calendar c=Calendar.getInstance();
			 int year=c.get(Calendar.YEAR);
			 int month=c.get(Calendar.MONTH)+1;
			 if(month<10){
				 queryBeginRegisterTimeStr=year+"-0"+month+"-01 00:00:00";
			 }else{
			 queryBeginRegisterTimeStr=year+"-"+month+"-01 00:00:00";
			 }
		 }
		 if(queryEndRegisterTimeStr.equals("")){
			 SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 queryEndRegisterTimeStr=sdf.format(new Date());
		 }
		List<String> dates=CalendarUtil.getBtDates(queryBeginRegisterTimeStr, queryEndRegisterTimeStr);//获取一个时间段内的每一天，为空的赋值为0
		 List<UserDay> resultDates=new ArrayList<UserDay>();
		 if(dates!=null){
			 for(String date:dates){
				 UserDay userDay=new UserDay();
				 userDay.setCount(0);
				 userDay.setDays(date);
				 for(UserDay ud:userCount){
					 if(date.equals(ud.getDays())){
						 userDay.setCount(ud.getCount());
					 }
				 }
				 resultDates.add(userDay);
			 } 
		 }
		return resultDates; 
		
		
	}

	@Override
	public List<User> findErrorUser(User user, PageUtil pageUtil) {
		List<User>errorUsers=userDao.findErrorUser(user, pageUtil);
		return errorUsers;
	}


	@Override
	public long findCountErrorUser(User user) {
		long count=userDao.findCountErrorUser(user);
		return count;
	}

	@Override
	public List<UserDay> findCountByHour(String queryBeginRegisterTimeStr,
			String queryEndRegisterTimeStr) {
		 List<UserDay> userCount=userDao.findCountByHour(queryBeginRegisterTimeStr, queryEndRegisterTimeStr);
		 if(queryBeginRegisterTimeStr.equals("") ){
			 Calendar c=Calendar.getInstance();
			 int year=c.get(Calendar.YEAR);
			 int month=c.get(Calendar.MONTH)+1;
			 if(month<10){
				 queryBeginRegisterTimeStr=year+"-0"+month+"-01 00:00:00";
			 }else{
			 queryBeginRegisterTimeStr=year+"-"+month+"-01 00:00:00";
			 }
		 }
		 if(queryEndRegisterTimeStr.equals("")){
			 SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 queryEndRegisterTimeStr=sdf.format(new Date());
		 }
		 String[] dates=CalendarUtil.getHours();
		 List<UserDay> resultDates=new ArrayList<UserDay>();
		 if(dates!=null){
			 for(String date:dates){
				 UserDay userDay=new UserDay();
				 userDay.setCount(0);
				 userDay.setDays(date);
				 for(UserDay ud:userCount){
					 if(date.equals(ud.getDays())){
						 userDay.setCount(ud.getCount());
					 }
				 }
				 resultDates.add(userDay);
			 } 
		 }
		return resultDates; 
	}
}