package com.midai.miya.user.service;

import com.midai.miya.user.model.User;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface UserService {

     List<User> findByConditions(User user,PageUtil page);

     long findByConditionsCount(User user);

     void save(User user);

     void update(User user);

     void delete(User user);

}