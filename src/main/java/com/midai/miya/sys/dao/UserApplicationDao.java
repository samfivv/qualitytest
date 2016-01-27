package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.UserApplication;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserApplicationDao {

     List<UserApplication> findByConditions(@Param("userApplication")UserApplication userApplication,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userApplication")UserApplication userApplication);

     void save(@Param("userApplication")UserApplication userApplication);

     void update(@Param("userApplication")UserApplication userApplication);

     void delete(@Param("userApplication")UserApplication userApplication);
     
     UserApplication findById(String applicationId);

}