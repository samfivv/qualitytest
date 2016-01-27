package com.midai.miya.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.user.dao.UserOftenChangeInfoDao;
import com.midai.miya.user.service.UserOftenChangeInfoService;
import com.midai.miya.user.model.UserOftenChangeInfo;
import com.midai.miya.utils.PageUtil;

@Service
public class UserOftenChangeInfoServiceImpl implements UserOftenChangeInfoService {

     @Autowired
     private UserOftenChangeInfoDao userOftenChangeInfoDao;

     @Override
     public List<UserOftenChangeInfo> findByConditions(UserOftenChangeInfo userOftenChangeInfo,PageUtil page) {
        List<UserOftenChangeInfo> lists=userOftenChangeInfoDao.findByConditions(userOftenChangeInfo,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserOftenChangeInfo userOftenChangeInfo) {
        long count=userOftenChangeInfoDao.findByConditionsCount(userOftenChangeInfo);
        return count;
     }

     @Override
     public void save(UserOftenChangeInfo userOftenChangeInfo) {
        userOftenChangeInfoDao.save(userOftenChangeInfo);
     }

     @Override
     public void update(UserOftenChangeInfo userOftenChangeInfo) {
        userOftenChangeInfoDao.update(userOftenChangeInfo);
     }

     @Override
     public void delete(UserOftenChangeInfo userOftenChangeInfo) {
        userOftenChangeInfoDao.delete(userOftenChangeInfo);
     }

     @Override
     public UserOftenChangeInfo findById(String userId){
        UserOftenChangeInfo UserOftenChangeInfo = userOftenChangeInfoDao.findById(userId);
        return UserOftenChangeInfo;
     }

     @Override
     public void updateUserLevel(String userId){
        userOftenChangeInfoDao.updateUserLevel(userId);
     }
}