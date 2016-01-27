package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.InterestBoutique;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface InterestBoutiqueDao {

     List<InterestBoutique> findByConditions(@Param("interestBoutique")InterestBoutique interestBoutique,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("interestBoutique")InterestBoutique interestBoutique);

     void save(@Param("interestBoutique")InterestBoutique interestBoutique);

     void update(@Param("interestBoutique")InterestBoutique interestBoutique);

     void delete(@Param("interestBoutique")InterestBoutique interestBoutique);


   	 InterestBoutique findInterestBoutiqueById(String interestBoutiqueId);
}