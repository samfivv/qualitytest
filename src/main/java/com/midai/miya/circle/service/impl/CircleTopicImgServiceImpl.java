package com.midai.miya.circle.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.circle.dao.CircleTopicImgDao;
import com.midai.miya.circle.service.CircleTopicImgService;
import com.midai.miya.circle.model.CircleTopicImg;
import com.midai.miya.utils.PageUtil;

@Service
public class CircleTopicImgServiceImpl implements CircleTopicImgService {

     @Autowired
     private CircleTopicImgDao circleTopicImgDao;

     @Override
     public List<CircleTopicImg> findByConditions(CircleTopicImg circleTopicImg,PageUtil page) {
        List<CircleTopicImg> lists=circleTopicImgDao.findByConditions(circleTopicImg,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(CircleTopicImg circleTopicImg) {
        long count=circleTopicImgDao.findByConditionsCount(circleTopicImg);
        return count;
     }

     @Override
     public void save(CircleTopicImg circleTopicImg) {
        circleTopicImgDao.save(circleTopicImg);
     }

     @Override
     public void update(CircleTopicImg circleTopicImg) {
        circleTopicImgDao.update(circleTopicImg);
     }

     @Override
     public void delete(CircleTopicImg circleTopicImg) {
        circleTopicImgDao.delete(circleTopicImg);
     }
}

