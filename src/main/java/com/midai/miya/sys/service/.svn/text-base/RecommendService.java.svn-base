package com.midai.miya.sys.service;

import com.midai.miya.sys.model.Interest;
import com.midai.miya.sys.model.Recommend;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface RecommendService {

     List<Recommend> findByConditions(Recommend recommend,PageUtil page);

     long findByConditionsCount(Recommend recommend);

     void save(Recommend recommend);

     void update(Recommend recommend);

     void delete(Recommend recommend);
     
     Recommend findById(String recommendId);
     
     List<Interest> findInterestByConditions(Interest interest,PageUtil page);
     
     long findInterestByConditionsCount(Interest interest);
     
     Interest findInterestById(String interestId);
     
     long findByInterestId(Recommend recommend);

}