package com.midai.miya.user.dao;

import com.midai.miya.user.model.UserExperienceLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserExperienceLogDao {

     List<UserExperienceLog> findByConditions(@Param("userExperienceLog")UserExperienceLog userExperienceLog,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userExperienceLog")UserExperienceLog userExperienceLog);

     void save(@Param("userExperienceLog")UserExperienceLog userExperienceLog);

     void update(@Param("userExperienceLog")UserExperienceLog userExperienceLog);

     void delete(@Param("userExperienceLog")UserExperienceLog userExperienceLog);

}