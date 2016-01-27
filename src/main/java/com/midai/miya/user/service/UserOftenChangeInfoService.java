package com.midai.miya.user.service;

import com.midai.miya.user.model.UserOftenChangeInfo;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface UserOftenChangeInfoService {

     List<UserOftenChangeInfo> findByConditions(UserOftenChangeInfo userOftenChangeInfo,PageUtil page);

     long findByConditionsCount(UserOftenChangeInfo userOftenChangeInfo);

     void save(UserOftenChangeInfo userOftenChangeInfo);

     void update(UserOftenChangeInfo userOftenChangeInfo);

     void delete(UserOftenChangeInfo userOftenChangeInfo);


   	UserOftenChangeInfo findById(String userId);

   	void updateUserLevel(String userId);
}