package com.midai.miya.sys.service;

import com.midai.miya.sys.model.Interest;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface InterestService {

     List<Interest> findByConditions(Interest interest,PageUtil page);

     long findByConditionsCount(Interest interest);

     void save(Interest interest);

     void update(Interest interest);

     void delete(Interest interest);
     
     Interest findInterestById(String interestId);


     List<Interest> findAllInterest(Interest interest,PageUtil page);

   	long findAllInterestCount(Interest interest);


   	Interest findInterestByInterestId(String interestId);

   	List<Interest> findAllInterestByUser(Interest interest,Integer type);

   	long findAllInterestCountByUser(Interest interest,Integer type);
   	
   	void updateBigLongImgUrl(Interest interest);
}