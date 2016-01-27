package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.RecommendUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface RecommendUserDao {

     List<RecommendUser> findByConditions(@Param("recommendUser")RecommendUser recommendUser,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("recommendUser")RecommendUser recommendUser);

     void save(@Param("recommendUser")RecommendUser recommendUser);

     void update(@Param("recommendUser")RecommendUser recommendUser);

     void delete(@Param("recommendUser")RecommendUser recommendUser);
     
     RecommendUser findById(@Param("recommendUserId")String recommendUserId);
     
     long findByUserId(@Param("userId")String userId);

}