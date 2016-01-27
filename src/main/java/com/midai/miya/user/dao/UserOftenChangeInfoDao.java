package com.midai.miya.user.dao;

import com.midai.miya.user.model.UserOftenChangeInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserOftenChangeInfoDao {

     List<UserOftenChangeInfo> findByConditions(@Param("userOftenChangeInfo")UserOftenChangeInfo userOftenChangeInfo,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userOftenChangeInfo")UserOftenChangeInfo userOftenChangeInfo);

     void save(@Param("userOftenChangeInfo")UserOftenChangeInfo userOftenChangeInfo);

     void update(@Param("userOftenChangeInfo")UserOftenChangeInfo userOftenChangeInfo);

     void delete(@Param("userOftenChangeInfo")UserOftenChangeInfo userOftenChangeInfo);


   	 UserOftenChangeInfo findById(@Param("userId")String userId);

   	 void updateUserLevel(String userId);
}