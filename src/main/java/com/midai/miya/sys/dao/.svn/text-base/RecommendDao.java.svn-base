package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.Recommend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface RecommendDao {

     List<Recommend> findByConditions(@Param("recommend")Recommend recommend,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("recommend")Recommend recommend);

     void save(@Param("recommend")Recommend recommend);

     void update(@Param("recommend")Recommend recommend);

     void delete(@Param("recommend")Recommend recommend);
     
     Recommend findById(@Param("recommendId")String recommendId);
     
     long findByInterestId(@Param("recommend")Recommend recommend);

}