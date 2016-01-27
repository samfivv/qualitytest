package com.midai.miya.user.dao;

import com.midai.miya.user.model.UserMoneyRmbLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserMoneyRmbLogDao {

     List<UserMoneyRmbLog> findByConditions(@Param("userMoneyRmbLog")UserMoneyRmbLog userMoneyRmbLog,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userMoneyRmbLog")UserMoneyRmbLog userMoneyRmbLog);

     void save(@Param("userMoneyRmbLog")UserMoneyRmbLog userMoneyRmbLog);

     void update(@Param("userMoneyRmbLog")UserMoneyRmbLog userMoneyRmbLog);

     void delete(@Param("userMoneyRmbLog")UserMoneyRmbLog userMoneyRmbLog);

}