package com.midai.miya.circle.dao;

import com.midai.miya.circle.model.Circle;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface CircleDao {

     List<Circle> findByConditions(@Param("circle")Circle circle,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("circle")Circle circle);

     void save(@Param("circle")Circle circle);

     void update(@Param("circle")Circle circle);

     void delete(@Param("circle")Circle circle);


   	 Circle findCircleById(@Param("circleId")String circleId);

   	 int findCircleByName(@Param("circle")Circle circle);

   	 List<Circle> findCircleByRecommend(@Param("circle")Circle circle,@Param("page")PageUtil page);

   	 long findCircleCountByRecommend(@Param("circle")Circle circle);
}