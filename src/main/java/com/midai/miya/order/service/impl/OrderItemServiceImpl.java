package com.midai.miya.order.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.order.dao.OrderItemDao;
import com.midai.miya.order.service.OrderItemService;
import com.midai.miya.order.model.OrderItem;
import com.midai.miya.utils.PageUtil;

@Service
public class OrderItemServiceImpl implements OrderItemService {

     @Autowired
     private OrderItemDao orderItemDao;

     @Override
     public List<OrderItem> findByConditions(OrderItem orderItem,PageUtil page) {
        List<OrderItem> lists=orderItemDao.findByConditions(orderItem,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(OrderItem orderItem) {
        long count=orderItemDao.findByConditionsCount(orderItem);
        return count;
     }

     @Override
     public void save(OrderItem orderItem) {
        orderItemDao.save(orderItem);
     }

     @Override
     public void update(OrderItem orderItem) {
        orderItemDao.update(orderItem);
     }

     @Override
     public void delete(OrderItem orderItem) {
        orderItemDao.delete(orderItem);
     }
}

