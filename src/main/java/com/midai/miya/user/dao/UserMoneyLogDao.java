package com.midai.miya.user.dao;

import com.midai.miya.user.model.UserMoneyLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserMoneyLogDao {

     List<UserMoneyLog> findByConditions(@Param("userMoneyLog")UserMoneyLog userMoneyLog,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userMoneyLog")UserMoneyLog userMoneyLog);

     void save(@Param("userMoneyLog")UserMoneyLog userMoneyLog);

     void update(@Param("userMoneyLog")UserMoneyLog userMoneyLog);

     void delete(@Param("userMoneyLog")UserMoneyLog userMoneyLog);

}