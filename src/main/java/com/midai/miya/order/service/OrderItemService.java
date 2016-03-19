package com.midai.miya.order.service;

import com.midai.miya.order.model.OrderItem;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface OrderItemService {

     List<OrderItem> findByConditions(OrderItem orderItem,PageUtil page);

     long findByConditionsCount(OrderItem orderItem);

     void save(OrderItem orderItem);

     void update(OrderItem orderItem);

     void delete(OrderItem orderItem);

}