package com.midai.miya.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.user.dao.UserMoneyLogDao;
import com.midai.miya.user.dao.UserOftenChangeInfoDao;
import com.midai.miya.user.service.UserMoneyLogService;
import com.midai.miya.user.model.UserMoneyLog;
import com.midai.miya.user.model.UserOftenChangeInfo;
import com.midai.miya.utils.NumberUtil;
import com.midai.miya.utils.PageUtil;

@Service
public class UserMoneyLogServiceImpl implements UserMoneyLogService {

     @Autowired
     private UserMoneyLogDao userMoneyLogDao;
     @Autowired
     private UserOftenChangeInfoDao userOftenChangeInfoDao;

     @Override
     public List<UserMoneyLog> findByConditions(UserMoneyLog userMoneyLog,PageUtil page) {
        List<UserMoneyLog> lists=userMoneyLogDao.findByConditions(userMoneyLog,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserMoneyLog userMoneyLog) {
        long count=userMoneyLogDao.findByConditionsCount(userMoneyLog);
        return count;
     }

     @Override
     public void save(UserMoneyLog userMoneyLog) {
        userMoneyLogDao.save(userMoneyLog);
        UserOftenChangeInfo userOftenChangeInfo=userOftenChangeInfoDao.findById(userMoneyLog.getUserId());
        userOftenChangeInfo.setUserMoney(NumberUtil.add(userMoneyLog.getLogMoney(), userOftenChangeInfo.getUserMoney()));
        userOftenChangeInfoDao.update(userOftenChangeInfo);
     }

     @Override
     public void update(UserMoneyLog userMoneyLog) {
        userMoneyLogDao.update(userMoneyLog);
     }

     @Override
     public void delete(UserMoneyLog userMoneyLog) {
        userMoneyLogDao.delete(userMoneyLog);
     }
}

