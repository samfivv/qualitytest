package com.midai.miya.circle.dao;

import com.midai.miya.circle.model.CircleTopicImg;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface CircleTopicImgDao {

     List<CircleTopicImg> findByConditions(@Param("circleTopicImg")CircleTopicImg circleTopicImg,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("circleTopicImg")CircleTopicImg circleTopicImg);

     void save(@Param("circleTopicImg")CircleTopicImg circleTopicImg);

     void update(@Param("circleTopicImg")CircleTopicImg circleTopicImg);

     void delete(@Param("circleTopicImg")CircleTopicImg circleTopicImg);

}