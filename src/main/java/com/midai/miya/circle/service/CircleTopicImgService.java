package com.midai.miya.circle.service;

import com.midai.miya.circle.model.CircleTopicImg;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface CircleTopicImgService {

     List<CircleTopicImg> findByConditions(CircleTopicImg circleTopicImg,PageUtil page);

     long findByConditionsCount(CircleTopicImg circleTopicImg);

     void save(CircleTopicImg circleTopicImg);

     void update(CircleTopicImg circleTopicImg);

     void delete(CircleTopicImg circleTopicImg);

}