package com.midai.miya.circle.dao;

import com.midai.miya.circle.model.RecommendCircle;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface RecommendCircleDao {

     List<RecommendCircle> findByConditions(@Param("recommendCircle")RecommendCircle recommendCircle,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("recommendCircle")RecommendCircle recommendCircle);

     void save(@Param("recommendCircle")RecommendCircle recommendCircle);

     void update(@Param("recommendCircle")RecommendCircle recommendCircle);

     void delete(@Param("recommendCircle")RecommendCircle recommendCircle);


   	 RecommendCircle findRecommendCircleById(@Param("recommendCircleId")String recommendCircleId);

   	 int findRecommendCircleCountById(@Param("recommendCircle")RecommendCircle recommendCircle);
}