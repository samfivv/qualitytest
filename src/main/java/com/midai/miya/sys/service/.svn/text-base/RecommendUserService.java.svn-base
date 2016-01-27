package com.midai.miya.sys.service;

import com.midai.miya.sys.model.RecommendUser;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface RecommendUserService {

     List<RecommendUser> findByConditions(RecommendUser recommendUser,PageUtil page);

     long findByConditionsCount(RecommendUser recommendUser);

     void save(RecommendUser recommendUser);

     void update(RecommendUser recommendUser);

     void delete(RecommendUser recommendUser);
     
     RecommendUser findById(String recommendUserId);
     
     long findByUserId(String userId);
     
}