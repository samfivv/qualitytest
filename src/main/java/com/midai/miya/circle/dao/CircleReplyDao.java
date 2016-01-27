package com.midai.miya.circle.dao;

import com.midai.miya.circle.model.CircleReply;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface CircleReplyDao {

     List<CircleReply> findByConditions(@Param("circleReply")CircleReply circleReply,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("circleReply")CircleReply circleReply);

     void save(@Param("circleReply")CircleReply circleReply);

     void update(@Param("circleReply")CircleReply circleReply);

     void delete(@Param("circleReply")CircleReply circleReply);


   	 CircleReply findCircleReplyById(@Param("mywCircleReplyId")String mywCircleReplyId);
}