package com.midai.miya.order.dao;

import com.midai.miya.order.model.OrderItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface OrderItemDao {

     List<OrderItem> findByConditions(@Param("orderItem")OrderItem orderItem,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("orderItem")OrderItem orderItem);

     void save(@Param("orderItem")OrderItem orderItem);

     void update(@Param("orderItem")OrderItem orderItem);

     void delete(@Param("orderItem")OrderItem orderItem);

}