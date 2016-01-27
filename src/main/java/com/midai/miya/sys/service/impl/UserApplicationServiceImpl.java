package com.midai.miya.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.UserApplicationDao;
import com.midai.miya.sys.dao.VideoDao;
import com.midai.miya.sys.service.UserApplicationService;
import com.midai.miya.sys.service.VideoService;
import com.midai.miya.sys.model.Message;
import com.midai.miya.sys.model.UserApplication;
import com.midai.miya.user.dao.UserOftenChangeInfoDao;
import com.midai.miya.user.model.User;
import com.midai.miya.user.model.UserOftenChangeInfo;
import com.midai.miya.user.service.UserService;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

     @Autowired
     private UserApplicationDao userApplicationDao;
     @Autowired
     private UserService userService;
     @Autowired
     private VideoDao videoDao;
     @Autowired
     private UserOftenChangeInfoDao userOftenChangeInfoDao;

     @Override
     public List<UserApplication> findByConditions(UserApplication userApplication,PageUtil page) {
    	 if(userApplication.getUserId()!=null){
    		 userApplication.setUserId(userApplication.getUserId().replace(" ", ""));
    	 }
    	 if(userApplication.getApplicationName()!=null){
    		 userApplication.setApplicationName(userApplication.getApplicationName().replace(" ", ""));
    	 }
        List<UserApplication> lists=userApplicationDao.findByConditions(userApplication,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserApplication userApplication) {
    	 if(userApplication.getUserId()!=null){
    		 userApplication.setUserId(userApplication.getUserId().replace(" ", ""));
    	 }
    	 if(userApplication.getApplicationName()!=null){
    		 userApplication.setApplicationName(userApplication.getApplicationName().replace(" ", ""));
    	 }
        long count=userApplicationDao.findByConditionsCount(userApplication);
        return count;
     }

     @Override
     public void save(UserApplication userApplication) {
        userApplicationDao.save(userApplication);
     }

     @Override
     public void update(UserApplication userApplication){ 
    	 Message message=new Message();
    	 User user=new User();
		 user.setUserId(userApplication.getUserId());
    	 if(userApplication.getApplicationState()==1){
    		 userApplication.setNotPassReasonState("");
    		 userApplication.setApplicationNotPassReason("");
    		 user.setUserIsauthor(1);
    		 user.setUserType(1);
    		 message.setMessageContent("恭喜你，成为了达人！请重新登录！");
    		 UserOftenChangeInfo userOftenChangeInfo=userOftenChangeInfoDao.findById(userApplication.getUserId());
    		 userOftenChangeInfo.setHipsterType(1);
    		 userOftenChangeInfoDao.update(userOftenChangeInfo);
    	 }else if(userApplication.getApplicationState()==2){
    		 message.setMessageContent("很遗憾，你还不能成为达人人，原因是"+userApplication.getApplicationNotPassReason()+"。");
    	 }
    	    userService.update(user);
    	    message.setMessageCreateTime(new Date());
			message.setMessageId(UUIDUtil.getUUID());
			message.setMessageState(0);
			message.setMessageType(0);
			message.setMessageUserId(userApplication.getUserId());
			videoDao.addMessage(message);
            userApplicationDao.update(userApplication);
     }

     @Override
     public void delete(UserApplication userApplication) {
        userApplicationDao.delete(userApplication);
     }

	@Override
	public UserApplication findById(String applicationId) {
		UserApplication userApplication=userApplicationDao.findById(applicationId);
		return userApplication;
	}
}

