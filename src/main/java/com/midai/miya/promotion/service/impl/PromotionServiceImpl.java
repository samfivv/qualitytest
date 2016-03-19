package com.midai.miya.promotion.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.promotion.dao.PromotionDao;
import com.midai.miya.promotion.service.PromotionService;
import com.midai.miya.promotion.model.Promotion;
import com.midai.miya.utils.PageUtil;

@Service
public class PromotionServiceImpl implements PromotionService {

     @Autowired
     private PromotionDao promotionDao;

     @Override
     public List<Promotion> findByConditions(Promotion promotion,PageUtil page) {
        List<Promotion> lists=promotionDao.findByConditions(promotion,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Promotion promotion) {
        long count=promotionDao.findByConditionsCount(promotion);
        return count;
     }

     @Override
     public void save(Promotion promotion) {
        promotionDao.save(promotion);
     }

     @Override
     public void update(Promotion promotion) {
        promotionDao.update(promotion);
     }

     @Override
     public void delete(Promotion promotion) {
        promotionDao.delete(promotion);
     }
}

