package com.midai.miya.circle.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.circle.dao.CircleDao;
import com.midai.miya.circle.service.CircleService;
import com.midai.miya.circle.model.Circle;
import com.midai.miya.sys.dao.VideoDao;
import com.midai.miya.sys.model.Message;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;

@Service
public class CircleServiceImpl implements CircleService {

     @Autowired
     private CircleDao circleDao;
     @Autowired
 	private VideoDao videoDao;

     @Override
     public List<Circle> findByConditions(Circle circle,PageUtil page) {
        List<Circle> lists=circleDao.findByConditions(circle,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Circle circle) {
        long count=circleDao.findByConditionsCount(circle);
        return count;
     }

     @Override
     public void save(Circle circle) {
    	 if(circle.getCircleName()!=null){
    		 circle.setCircleName(circle.getCircleName().trim());
 		}
        circleDao.save(circle);
     }

     @Override
     public void update(Circle circle) {
    	 Circle circle1=circleDao.findCircleById(circle.getCircleId());
    	 if(circle.getCircleName()!=null){
    		 circle.setCircleName(circle.getCircleName().trim());
 		}
    	 Message message=new Message();
		 message.setMessageId(UUIDUtil.getUUID());
		 message.setMessageCreateTime(new Date());
		 message.setMessageType(0);
		 message.setMessageState(0);
		 message.setMessageUserId(circle.getUserId());
    	 if(circle.getCircleState()==2){
    		 message.setMessageContent("您的圈子"+circle1.getCircleName()+"没有通过审核,原因是"+circle.getUnpassDesc());
    	 }else if(circle.getCircleState()==1){
    		 message.setMessageContent("恭喜您！您的圈子"+circle1.getCircleName()+"通过了审核！");
    	 }
    	 videoDao.addMessage(message);
        circleDao.update(circle);
     }

     @Override
     public void delete(Circle circle) {
        circleDao.delete(circle);
     }

     @Override
     public Circle findCircleById(String circleId){
        Circle Circle = circleDao.findCircleById(circleId);
        return Circle;
     }


     @Override
     public int findCircleByName(Circle circle){
        int count = circleDao.findCircleByName(circle);
        return count;
     }

     @Override
     public List<Circle> findCircleByRecommend(Circle circle,PageUtil page){
        List<Circle> Circle = circleDao.findCircleByRecommend(circle,page);
        return Circle;
     }

     @Override
     public long findCircleCountByRecommend(Circle circle){
        long Circle = circleDao.findCircleCountByRecommend(circle);
        return Circle;
     }
}