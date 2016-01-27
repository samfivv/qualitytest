package com.midai.miya.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.user.dao.UserMoneyRmbLogDao;
import com.midai.miya.user.service.UserMoneyRmbLogService;
import com.midai.miya.user.model.UserMoneyRmbLog;
import com.midai.miya.utils.PageUtil;

@Service
public class UserMoneyRmbLogServiceImpl implements UserMoneyRmbLogService {

     @Autowired
     private UserMoneyRmbLogDao userMoneyRmbLogDao;

     @Override
     public List<UserMoneyRmbLog> findByConditions(UserMoneyRmbLog userMoneyRmbLog,PageUtil page) {
        List<UserMoneyRmbLog> lists=userMoneyRmbLogDao.findByConditions(userMoneyRmbLog,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserMoneyRmbLog userMoneyRmbLog) {
        long count=userMoneyRmbLogDao.findByConditionsCount(userMoneyRmbLog);
        return count;
     }

     @Override
     public void save(UserMoneyRmbLog userMoneyRmbLog) {
        userMoneyRmbLogDao.save(userMoneyRmbLog);
     }

     @Override
     public void update(UserMoneyRmbLog userMoneyRmbLog) {
        userMoneyRmbLogDao.update(userMoneyRmbLog);
     }

     @Override
     public void delete(UserMoneyRmbLog userMoneyRmbLog) {
        userMoneyRmbLogDao.delete(userMoneyRmbLog);
     }
}

