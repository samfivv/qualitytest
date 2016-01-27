package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.Interest;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface InterestDao {

     List<Interest> findByConditions(@Param("interest")Interest interest,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("interest")Interest interest);

     void save(@Param("interest")Interest interest);

     void update(@Param("interest")Interest interest);

     void delete(@Param("interest")Interest interest);
     
     Interest findInterestById(@Param("interestId")String interestId);
     
     String findInterestOftenChange(@Param("interestId")String interestId);
     
     Integer findInterestOftenChangeVideoCount(@Param("interestId")String interestId);
     
     void updateInterestOftenChange(@Param("interestVideoCount")Integer interestVideoCount,@Param("interestId")String interestId,@Param("interestVideoTimelong")String interestVideoTimelong);


     List<Interest> findAllInterest(@Param("interest")Interest interest,@Param("page")PageUtil page);

   	 long findAllInterestCount(@Param("interest")Interest interest);


   	 Interest findInterestByInterestId(@Param("interestId")String interestId);

   	 List<Interest> findAllInterestByUser(@Param("interest")Interest interest,@Param("type")Integer type);

   	 long findAllInterestCountByUser(@Param("interest")Interest interest,@Param("type")Integer type);
}