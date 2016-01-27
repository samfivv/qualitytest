package com.midai.miya.circle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.circle.dao.CircleTopicDao;
import com.midai.miya.circle.service.CircleTopicService;
import com.midai.miya.circle.model.CircleTopic;
import com.midai.miya.utils.PageUtil;

@Service
public class CircleTopicServiceImpl implements CircleTopicService {

     @Autowired
     private CircleTopicDao circleTopicDao;

     @Override
     public List<CircleTopic> findByConditions(CircleTopic circleTopic,PageUtil page) {
    	 if(circleTopic.getCircleTopicContent()!=null){
    		 circleTopic.setCircleTopicContent(circleTopic.getCircleTopicContent().replaceAll(" ", ""));
    	 }
    	 if(circleTopic.getCircleTopicTitle()!=null){
    		 circleTopic.setCircleTopicTitle(circleTopic.getCircleTopicTitle().replaceAll(" ", ""));
    	 }
        List<CircleTopic> lists=circleTopicDao.findByConditions(circleTopic,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(CircleTopic circleTopic) {
    	 if(circleTopic.getCircleTopicContent()!=null){
    		 circleTopic.setCircleTopicContent(circleTopic.getCircleTopicContent().replaceAll(" ", ""));
    	 }
    	 if(circleTopic.getCircleTopicTitle()!=null){
    		 circleTopic.setCircleTopicTitle(circleTopic.getCircleTopicTitle().replaceAll(" ", ""));
    	 }
        long count=circleTopicDao.findByConditionsCount(circleTopic);
        return count;
     }

     @Override
     public void save(CircleTopic circleTopic) {
        circleTopicDao.save(circleTopic);
     }

     @Override
     public void update(CircleTopic circleTopic) {
        circleTopicDao.update(circleTopic);
     }

     @Override
     public void delete(CircleTopic circleTopic) {
        circleTopicDao.delete(circleTopic);
     }

     @Override
     public CircleTopic findCircleTopicById(String circleTopicId){
        CircleTopic CircleTopic = circleTopicDao.findCircleTopicById(circleTopicId);
        return CircleTopic;
     }
}