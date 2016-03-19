package com.midai.miya.promotion.dao;

import com.midai.miya.promotion.model.Promotion;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface PromotionDao {

     List<Promotion> findByConditions(@Param("promotion")Promotion promotion,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("promotion")Promotion promotion);

     void save(@Param("promotion")Promotion promotion);

     void update(@Param("promotion")Promotion promotion);

     void delete(@Param("promotion")Promotion promotion);

}