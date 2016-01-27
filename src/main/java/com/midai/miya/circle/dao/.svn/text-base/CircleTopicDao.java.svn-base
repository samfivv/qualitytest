package com.midai.miya.circle.dao;

import com.midai.miya.circle.model.CircleTopic;
import com.midai.miya.utils.PageUtil;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CircleTopicDao {

     List<CircleTopic> findByConditions(@Param("circleTopic")CircleTopic circleTopic,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("circleTopic")CircleTopic circleTopic);

     void save(@Param("circleTopic")CircleTopic circleTopic);

     void update(@Param("circleTopic")CircleTopic circleTopic);

     void delete(@Param("circleTopic")CircleTopic circleTopic);


   	 CircleTopic findCircleTopicById(String circleTopicId);
}