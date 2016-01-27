package com.midai.miya.ask.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.ask.dao.AskReplyDao;
import com.midai.miya.ask.service.AskReplyService;
import com.midai.miya.ask.model.AskReply;
import com.midai.miya.utils.PageUtil;

@Service
public class AskReplyServiceImpl implements AskReplyService {

     @Autowired
     private AskReplyDao askReplyDao;

     @Override
     public List<AskReply> findByConditions(AskReply askReply,PageUtil page) {
    	 if(askReply.getReplyContent()!=null){
    		 askReply.setReplyContent(askReply.getReplyContent().replace(" ", ""));
    	 }
        List<AskReply> lists=askReplyDao.findByConditions(askReply,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(AskReply askReply) {
    	 if(askReply.getReplyContent()!=null){
    		 askReply.setReplyContent(askReply.getReplyContent().replace(" ", ""));
    	 }
        long count=askReplyDao.findByConditionsCount(askReply);
        return count;
     }

     @Override
     public void save(AskReply askReply) {
        askReplyDao.save(askReply);
     }

     @Override
     public void update(AskReply askReply) {
        askReplyDao.update(askReply);
     }

     @Override
     public void delete(AskReply askReply) {
        askReplyDao.delete(askReply);
     }

     @Override
     public AskReply findAskReplyById(String replyId){
        AskReply AskReply = askReplyDao.findAskReplyById(replyId);
        return AskReply;
     }
}