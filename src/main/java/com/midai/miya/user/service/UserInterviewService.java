package com.midai.miya.user.service;

import com.midai.miya.user.model.UserInterview;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface UserInterviewService {

     List<UserInterview> findByConditions(UserInterview userInterview,PageUtil page);

     long findByConditionsCount(UserInterview userInterview);

     void save(UserInterview userInterview);

     void update(UserInterview userInterview);

     void delete(UserInterview userInterview);


   	UserInterview findUserInterviewById(String userInterviewId);

   	int findUserInterviewCountBytitle(String userInterviewTitle,String userInterviewId);
}