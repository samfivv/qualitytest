package com.midai.miya.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.user.dao.UserUpgradeApplyDao;
import com.midai.miya.user.service.UserUpgradeApplyService;
import com.midai.miya.user.model.UserUpgradeApply;
import com.midai.miya.utils.PageUtil;

@Service
public class UserUpgradeApplyServiceImpl implements UserUpgradeApplyService {

     @Autowired
     private UserUpgradeApplyDao userUpgradeApplyDao;

     @Override
     public List<UserUpgradeApply> findByConditions(UserUpgradeApply userUpgradeApply,PageUtil page) {
        List<UserUpgradeApply> lists=userUpgradeApplyDao.findByConditions(userUpgradeApply,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserUpgradeApply userUpgradeApply) {
        long count=userUpgradeApplyDao.findByConditionsCount(userUpgradeApply);
        return count;
     }

     @Override
     public void save(UserUpgradeApply userUpgradeApply) {
        userUpgradeApplyDao.save(userUpgradeApply);
     }

     @Override
     public void update(UserUpgradeApply userUpgradeApply) {
        userUpgradeApplyDao.update(userUpgradeApply);
     }

     @Override
     public void delete(UserUpgradeApply userUpgradeApply) {
        userUpgradeApplyDao.delete(userUpgradeApply);
     }

     @Override
     public UserUpgradeApply findUpgradeApplyById(String userUpgradeApplyId){
        UserUpgradeApply UserUpgradeApply = userUpgradeApplyDao.findUpgradeApplyById(userUpgradeApplyId);
        return UserUpgradeApply;
     }
}