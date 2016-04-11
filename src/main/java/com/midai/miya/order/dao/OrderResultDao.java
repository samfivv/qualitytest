package com.midai.miya.order.dao;

import com.midai.miya.order.model.OrderResult;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface OrderResultDao {

     List<OrderResult> findByConditions(@Param("orderResult")OrderResult orderResult,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("orderResult")OrderResult orderResult);

     void save(@Param("orderResult")OrderResult orderResult);

     void update(@Param("orderResult")OrderResult orderResult);

     void delete(@Param("orderResult")OrderResult orderResult);
     /////////////////////////////////////////////////////////////
     OrderResult findById(@Param("resultId")String resultId);

}