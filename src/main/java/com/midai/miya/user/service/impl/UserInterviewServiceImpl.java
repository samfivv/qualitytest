package com.midai.miya.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.user.dao.UserInterviewDao;
import com.midai.miya.user.service.UserInterviewService;
import com.midai.miya.user.model.UserInterview;
import com.midai.miya.utils.PageUtil;

@Service
public class UserInterviewServiceImpl implements UserInterviewService {

     @Autowired
     private UserInterviewDao userInterviewDao;

     @Override
     public List<UserInterview> findByConditions(UserInterview userInterview,PageUtil page) {
        List<UserInterview> lists=userInterviewDao.findByConditions(userInterview,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserInterview userInterview) {
        long count=userInterviewDao.findByConditionsCount(userInterview);
        return count;
     }

     @Override
     public void save(UserInterview userInterview) {
    	 if(userInterview.getUserInterviewTitle()!=null){
    		 userInterview.setUserInterviewTitle(userInterview.getUserInterviewTitle().trim());
    	 }
    	 if(userInterview.getUserInterviewAbstract()!=null){
    		 userInterview.setUserInterviewAbstract(userInterview.getUserInterviewAbstract().trim());
    	 }
        userInterviewDao.save(userInterview);
     }

     @Override
     public void update(UserInterview userInterview) {
    	 if(userInterview.getUserInterviewTitle()!=null){
    		 userInterview.setUserInterviewTitle(userInterview.getUserInterviewTitle().trim());
    	 }
    	 if(userInterview.getUserInterviewAbstract()!=null){
    		 userInterview.setUserInterviewAbstract(userInterview.getUserInterviewAbstract().trim());
    	 }
        userInterviewDao.update(userInterview);
     }

     @Override
     public void delete(UserInterview userInterview) {
        userInterviewDao.delete(userInterview);
     }

     @Override
     public UserInterview findUserInterviewById(String userInterviewId){
        UserInterview UserInterview = userInterviewDao.findUserInterviewById(userInterviewId);
        return UserInterview;
     }

     @Override
     public int findUserInterviewCountBytitle(String userInterviewTitle,String userInterviewId){
        int UserInterview = userInterviewDao.findUserInterviewCountBytitle(userInterviewTitle,userInterviewId);
        return UserInterview;
     }
}