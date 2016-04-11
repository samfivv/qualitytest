package com.midai.miya.order.dao;

import com.midai.miya.order.model.OrderResultItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface OrderResultItemDao {

     List<OrderResultItem> findByConditions(@Param("orderResultItem")OrderResultItem orderResultItem,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("orderResultItem")OrderResultItem orderResultItem);

     void save(@Param("orderResultItem")OrderResultItem orderResultItem);

     void update(@Param("orderResultItem")OrderResultItem orderResultItem);

     void delete(@Param("orderResultItem")OrderResultItem orderResultItem);

}