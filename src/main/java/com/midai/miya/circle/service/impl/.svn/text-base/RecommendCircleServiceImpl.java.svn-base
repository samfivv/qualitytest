package com.midai.miya.circle.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.circle.dao.RecommendCircleDao;
import com.midai.miya.circle.service.RecommendCircleService;
import com.midai.miya.circle.model.RecommendCircle;
import com.midai.miya.utils.PageUtil;

@Service
public class RecommendCircleServiceImpl implements RecommendCircleService {

     @Autowired
     private RecommendCircleDao recommendCircleDao;

     @Override
     public List<RecommendCircle> findByConditions(RecommendCircle recommendCircle,PageUtil page) {
        List<RecommendCircle> lists=recommendCircleDao.findByConditions(recommendCircle,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(RecommendCircle recommendCircle) {
        long count=recommendCircleDao.findByConditionsCount(recommendCircle);
        return count;
     }

     @Override
     public void save(RecommendCircle recommendCircle) {
        recommendCircleDao.save(recommendCircle);
     }

     @Override
     public void update(RecommendCircle recommendCircle) {
        recommendCircleDao.update(recommendCircle);
     }

     @Override
     public void delete(RecommendCircle recommendCircle) {
        recommendCircleDao.delete(recommendCircle);
     }

     @Override
     public RecommendCircle findRecommendCircleById(String recommendCircleId){
        RecommendCircle RecommendCircle = recommendCircleDao.findRecommendCircleById(recommendCircleId);
        return RecommendCircle;
     }

     @Override
     public int findRecommendCircleCountById(RecommendCircle recommendCircle){
        int RecommendCircle = recommendCircleDao.findRecommendCircleCountById(recommendCircle);
        return RecommendCircle;
     }
}