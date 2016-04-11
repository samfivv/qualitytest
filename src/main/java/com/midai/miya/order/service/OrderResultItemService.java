package com.midai.miya.order.service;

import com.midai.miya.order.model.OrderResultItem;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface OrderResultItemService {

     List<OrderResultItem> findByConditions(OrderResultItem orderResultItem,PageUtil page);

     long findByConditionsCount(OrderResultItem orderResultItem);

     void save(OrderResultItem orderResultItem);

     void update(OrderResultItem orderResultItem);

     void delete(OrderResultItem orderResultItem);

}