package com.midai.miya.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.OrganizationApplicationDao;
import com.midai.miya.sys.dao.VideoDao;
import com.midai.miya.sys.service.OrganizationApplicationService;
import com.midai.miya.sys.model.Message;
import com.midai.miya.sys.model.OrganizationApplication;
import com.midai.miya.user.dao.UserOftenChangeInfoDao;
import com.midai.miya.user.model.User;
import com.midai.miya.user.model.UserOftenChangeInfo;
import com.midai.miya.user.service.UserService;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;

@Service
public class OrganizationApplicationServiceImpl implements OrganizationApplicationService {

     @Autowired
     private OrganizationApplicationDao organizationApplicationDao;
     @Autowired
     private UserService  userService;
     @Autowired
     private VideoDao videoDao;
     @Autowired
     private UserOftenChangeInfoDao userOftenChangeInfoDao;

     @Override
     public List<OrganizationApplication> findByConditions(OrganizationApplication organizationApplication,PageUtil page) {
    	 if(organizationApplication.getUserMail()!=null){
    		 organizationApplication.setUserMail(organizationApplication.getUserMail().replace(" ", ""));
    	 }
    	 if(organizationApplication.getOrganizationName()!=null){
    		 organizationApplication.setOrganizationName(organizationApplication.getOrganizationName().replace(" ", ""));
    	 }
        List<OrganizationApplication> lists=organizationApplicationDao.findByConditions(organizationApplication,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(OrganizationApplication organizationApplication) {
    	 if(organizationApplication.getUserMail()!=null){
    		 organizationApplication.setUserMail(organizationApplication.getUserMail().replace(" ", ""));
    	 }
    	 if(organizationApplication.getOrganizationName()!=null){
    		 organizationApplication.setOrganizationName(organizationApplication.getOrganizationName().replace(" ", ""));
    	 }
        long count=organizationApplicationDao.findByConditionsCount(organizationApplication);
        return count;
     }

     @Override
     public void save(OrganizationApplication organizationApplication) {
        organizationApplicationDao.save(organizationApplication);
     }

     @Override
     public void update(OrganizationApplication organizationApplication) {
    	 Message message=new Message();
    	 User user=new User();
		 user.setUserId(organizationApplication.getUserId());
    	 if(organizationApplication.getOrganizationState()==1){
    		 organizationApplication.setNotPassReasonState("");
    		 organizationApplication.setApplicationNotPassReason("");
    		 user.setUserIsauthor(1);
    		 user.setUserType(2);
    		 message.setMessageContent("恭喜你，成为了机构达人！请重新登录！");
    		 UserOftenChangeInfo userOftenChangeInfo=userOftenChangeInfoDao.findById(organizationApplication.getUserId());
    		 userOftenChangeInfo.setHipsterType(1);
    		 userOftenChangeInfoDao.update(userOftenChangeInfo);
    	 }else if(organizationApplication.getOrganizationState()==2){
    		 message.setMessageContent("很遗憾，你还不能成为机构达人，原因是"+organizationApplication.getApplicationNotPassReason()+"。");
    			user.setUserIsauthor(0);
    			user.setUserType(0);
    	 }
    	    userService.update(user);
    	    message.setMessageCreateTime(new Date());
			message.setMessageId(UUIDUtil.getUUID());
			message.setMessageState(0);
			message.setMessageType(0);
			message.setMessageUserId(organizationApplication.getUserId());
			videoDao.addMessage(message);
            organizationApplicationDao.update(organizationApplication);
     }

     @Override
     public void delete(OrganizationApplication organizationApplication) {
        organizationApplicationDao.delete(organizationApplication);
     }

     @Override
     public OrganizationApplication findById(String organizationApplicationId){
        OrganizationApplication OrganizationApplication = organizationApplicationDao.findById(organizationApplicationId);
        return OrganizationApplication;
     }
}