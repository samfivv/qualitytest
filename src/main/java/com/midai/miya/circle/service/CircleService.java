package com.midai.miya.circle.service;

import com.midai.miya.circle.model.Circle;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface CircleService {

     List<Circle> findByConditions(Circle circle,PageUtil page);

     long findByConditionsCount(Circle circle);

     void save(Circle circle);

     void update(Circle circle);

     void delete(Circle circle);


   	Circle findCircleById(String circleId);

   	int findCircleByName(Circle circle);

   	List<Circle> findCircleByRecommend(Circle circle,PageUtil page);

   	long findCircleCountByRecommend(Circle circle);
}