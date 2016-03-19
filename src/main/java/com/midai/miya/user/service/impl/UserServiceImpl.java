package com.midai.miya.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.user.dao.UserDao;
import com.midai.miya.user.service.UserService;
import com.midai.miya.user.model.User;
import com.midai.miya.utils.PageUtil;

@Service
public class UserServiceImpl implements UserService {

     @Autowired
     private UserDao userDao;

     @Override
     public List<User> findByConditions(User user,PageUtil page) {
        List<User> lists=userDao.findByConditions(user,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(User user) {
        long count=userDao.findByConditionsCount(user);
        return count;
     }

     @Override
     public void save(User user) {
        userDao.save(user);
     }

     @Override
     public void update(User user) {
        userDao.update(user);
     }

     @Override
     public void delete(User user) {
        userDao.delete(user);
     }
}

