package com.midai.miya.ask.dao;

import com.midai.miya.ask.model.AskReply;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface AskReplyDao {

     List<AskReply> findByConditions(@Param("askReply")AskReply askReply,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("askReply")AskReply askReply);

     void save(@Param("askReply")AskReply askReply);

     void update(@Param("askReply")AskReply askReply);

     void delete(@Param("askReply")AskReply askReply);


   	 AskReply findAskReplyById(@Param("replyId")String replyId);
}