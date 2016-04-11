package com.midai.miya.order.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.order.dao.OrderAddtitionalItemDao;
import com.midai.miya.order.service.OrderAddtitionalItemService;
import com.midai.miya.order.model.OrderAddtitionalItem;
import com.midai.miya.utils.PageUtil;

@Service
public class OrderAddtitionalItemServiceImpl implements OrderAddtitionalItemService {

     @Autowired
     private OrderAddtitionalItemDao orderAddtitionalItemDao;

     @Override
     public List<OrderAddtitionalItem> findByConditions(OrderAddtitionalItem orderAddtitionalItem,PageUtil page) {
        List<OrderAddtitionalItem> lists=orderAddtitionalItemDao.findByConditions(orderAddtitionalItem,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(OrderAddtitionalItem orderAddtitionalItem) {
        long count=orderAddtitionalItemDao.findByConditionsCount(orderAddtitionalItem);
        return count;
     }

     @Override
     public void save(OrderAddtitionalItem orderAddtitionalItem) {
        orderAddtitionalItemDao.save(orderAddtitionalItem);
     }

     @Override
     public void update(OrderAddtitionalItem orderAddtitionalItem) {
        orderAddtitionalItemDao.update(orderAddtitionalItem);
     }

     @Override
     public void delete(OrderAddtitionalItem orderAddtitionalItem) {
        orderAddtitionalItemDao.delete(orderAddtitionalItem);
     }
}

