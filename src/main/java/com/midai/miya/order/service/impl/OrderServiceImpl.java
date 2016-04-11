package com.midai.miya.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.order.dao.OrderDao;
import com.midai.miya.order.service.OrderService;
import com.midai.miya.order.model.Order;
import com.midai.miya.utils.PageUtil;

@Service
public class OrderServiceImpl implements OrderService {

     @Autowired
     private OrderDao orderDao;

     @Override
     public List<Order> findByConditions(Order order,PageUtil page) {
        List<Order> lists=orderDao.findByConditions(order,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Order order) {
        long count=orderDao.findByConditionsCount(order);
        return count;
     }

     @Override
     public void save(Order order) {
        orderDao.save(order);
     }

     @Override
     public void update(Order order) {
        orderDao.update(order);
     }

     @Override
     public void delete(Order order) {
        orderDao.delete(order);
     }

     ///////////////////////////////////////////////////////////////
	@Override
	public Order findByBarcode(String materialBarcode) {
		return orderDao.findByBarcode(materialBarcode);
	}

	@Override
	public Order findByNo(String orderNo) {
		return orderDao.findByNo(orderNo);
	}
}

