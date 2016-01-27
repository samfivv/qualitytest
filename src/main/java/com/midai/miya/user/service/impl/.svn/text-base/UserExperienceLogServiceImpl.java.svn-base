package com.midai.miya.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.user.dao.UserExperienceLogDao;
import com.midai.miya.user.service.UserExperienceLogService;
import com.midai.miya.user.model.UserExperienceLog;
import com.midai.miya.utils.PageUtil;

@Service
public class UserExperienceLogServiceImpl implements UserExperienceLogService {

     @Autowired
     private UserExperienceLogDao userExperienceLogDao;

     @Override
     public List<UserExperienceLog> findByConditions(UserExperienceLog userExperienceLog,PageUtil page) {
        List<UserExperienceLog> lists=userExperienceLogDao.findByConditions(userExperienceLog,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserExperienceLog userExperienceLog) {
        long count=userExperienceLogDao.findByConditionsCount(userExperienceLog);
        return count;
     }

     @Override
     public void save(UserExperienceLog userExperienceLog) {
        userExperienceLogDao.save(userExperienceLog);
     }

     @Override
     public void update(UserExperienceLog userExperienceLog) {
        userExperienceLogDao.update(userExperienceLog);
     }

     @Override
     public void delete(UserExperienceLog userExperienceLog) {
        userExperienceLogDao.delete(userExperienceLog);
     }
}

