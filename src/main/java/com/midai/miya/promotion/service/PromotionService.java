package com.midai.miya.promotion.service;

import com.midai.miya.promotion.model.Promotion;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface PromotionService {

     List<Promotion> findByConditions(Promotion promotion,PageUtil page);

     long findByConditionsCount(Promotion promotion);

     void save(Promotion promotion);

     void update(Promotion promotion);

     void delete(Promotion promotion);

}