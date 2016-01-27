package com.midai.miya.user.dao;

import com.midai.miya.user.model.UserInterview;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserInterviewDao {

     List<UserInterview> findByConditions(@Param("userInterview")UserInterview userInterview,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userInterview")UserInterview userInterview);

     void save(@Param("userInterview")UserInterview userInterview);

     void update(@Param("userInterview")UserInterview userInterview);

     void delete(@Param("userInterview")UserInterview userInterview);


   	 UserInterview findUserInterviewById(@Param("userInterviewId")String userInterviewId);

   	 int findUserInterviewCountBytitle(@Param("userInterviewTitle")String userInterviewTitle,@Param("userInterviewId")String userInterviewId);
}