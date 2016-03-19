package com.midai.miya.order.service;

import com.midai.miya.order.model.Order;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface OrderService {

     List<Order> findByConditions(Order order,PageUtil page);

     long findByConditionsCount(Order order);

     void save(Order order);

     void update(Order order);

     void delete(Order order);

}