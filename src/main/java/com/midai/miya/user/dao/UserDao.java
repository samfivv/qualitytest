package com.midai.miya.user.dao;

import com.midai.miya.user.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserDao {

     List<User> findByConditions(@Param("user")User user,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("user")User user);

     void save(@Param("user")User user);

     void update(@Param("user")User user);

     void delete(@Param("user")User user);

}