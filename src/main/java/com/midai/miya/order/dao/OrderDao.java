package com.midai.miya.order.dao;

import com.midai.miya.order.model.Order;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface OrderDao {

     List<Order> findByConditions(@Param("order")Order order,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("order")Order order);

     void save(@Param("order")Order order);

     void update(@Param("order")Order order);

     void delete(@Param("order")Order order);

}