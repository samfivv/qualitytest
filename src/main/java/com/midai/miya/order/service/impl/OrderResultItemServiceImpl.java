package com.midai.miya.order.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.order.dao.OrderResultItemDao;
import com.midai.miya.order.service.OrderResultItemService;
import com.midai.miya.order.model.OrderResultItem;
import com.midai.miya.utils.PageUtil;

@Service
public class OrderResultItemServiceImpl implements OrderResultItemService {

     @Autowired
     private OrderResultItemDao orderResultItemDao;

     @Override
     public List<OrderResultItem> findByConditions(OrderResultItem orderResultItem,PageUtil page) {
        List<OrderResultItem> lists=orderResultItemDao.findByConditions(orderResultItem,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(OrderResultItem orderResultItem) {
        long count=orderResultItemDao.findByConditionsCount(orderResultItem);
        return count;
     }

     @Override
     public void save(OrderResultItem orderResultItem) {
        orderResultItemDao.save(orderResultItem);
     }

     @Override
     public void update(OrderResultItem orderResultItem) {
        orderResultItemDao.update(orderResultItem);
     }

     @Override
     public void delete(OrderResultItem orderResultItem) {
        orderResultItemDao.delete(orderResultItem);
     }
}

