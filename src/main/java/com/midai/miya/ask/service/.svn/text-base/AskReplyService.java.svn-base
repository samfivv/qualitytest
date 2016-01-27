package com.midai.miya.ask.service;

import com.midai.miya.ask.model.AskReply;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface AskReplyService {

     List<AskReply> findByConditions(AskReply askReply,PageUtil page);

     long findByConditionsCount(AskReply askReply);

     void save(AskReply askReply);

     void update(AskReply askReply);

     void delete(AskReply askReply);


   	AskReply findAskReplyById(String replyId);
}