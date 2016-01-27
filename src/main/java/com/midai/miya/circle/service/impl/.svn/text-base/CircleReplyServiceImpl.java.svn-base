package com.midai.miya.circle.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.circle.dao.CircleReplyDao;
import com.midai.miya.circle.service.CircleReplyService;
import com.midai.miya.circle.model.CircleReply;
import com.midai.miya.utils.PageUtil;

@Service
public class CircleReplyServiceImpl implements CircleReplyService {

     @Autowired
     private CircleReplyDao circleReplyDao;

     @Override
     public List<CircleReply> findByConditions(CircleReply circleReply,PageUtil page) {
    	 if(circleReply.getReplyContent()!=null){
    		 circleReply.setReplyContent(circleReply.getReplyContent().replaceAll(" ", ""));
    	 }
        List<CircleReply> lists=circleReplyDao.findByConditions(circleReply,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(CircleReply circleReply) {
    	 if(circleReply.getReplyContent()!=null){
    		 circleReply.setReplyContent(circleReply.getReplyContent().replaceAll(" ", ""));
    	 }
        long count=circleReplyDao.findByConditionsCount(circleReply);
        return count;
     }

     @Override
     public void save(CircleReply circleReply) {
        circleReplyDao.save(circleReply);
     }

     @Override
     public void update(CircleReply circleReply) {
        circleReplyDao.update(circleReply);
     }

     @Override
     public void delete(CircleReply circleReply) {
        circleReplyDao.delete(circleReply);
     }

     @Override
     public CircleReply findCircleReplyById(String mywCircleReplyId){
        CircleReply CircleReply = circleReplyDao.findCircleReplyById(mywCircleReplyId);
        return CircleReply;
     }
}