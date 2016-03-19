package com.midai.miya.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.user.dao.UserAccountDao;
import com.midai.miya.user.service.UserAccountService;
import com.midai.miya.user.model.UserAccount;
import com.midai.miya.utils.PageUtil;

@Service
public class UserAccountServiceImpl implements UserAccountService {

     @Autowired
     private UserAccountDao userAccountDao;

     @Override
     public List<UserAccount> findByConditions(UserAccount userAccount,PageUtil page) {
        List<UserAccount> lists=userAccountDao.findByConditions(userAccount,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserAccount userAccount) {
        long count=userAccountDao.findByConditionsCount(userAccount);
        return count;
     }

     @Override
     public void save(UserAccount userAccount) {
        userAccountDao.save(userAccount);
     }

     @Override
     public void update(UserAccount userAccount) {
        userAccountDao.update(userAccount);
     }

     @Override
     public void delete(UserAccount userAccount) {
        userAccountDao.delete(userAccount);
     }
}

