package com.midai.miya.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.order.dao.OrderResultDao;
import com.midai.miya.order.service.OrderResultService;
import com.midai.miya.order.model.OrderResult;
import com.midai.miya.utils.PageUtil;

@Service
public class OrderResultServiceImpl implements OrderResultService {

     @Autowired
     private OrderResultDao orderResultDao;

     @Override
     public List<OrderResult> findByConditions(OrderResult orderResult,PageUtil page) {
        List<OrderResult> lists=orderResultDao.findByConditions(orderResult,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(OrderResult orderResult) {
        long count=orderResultDao.findByConditionsCount(orderResult);
        return count;
     }

     @Override
     public void save(OrderResult orderResult) {
        orderResultDao.save(orderResult);
     }

     @Override
     public void update(OrderResult orderResult) {
        orderResultDao.update(orderResult);
     }

     @Override
     public void delete(OrderResult orderResult) {
        orderResultDao.delete(orderResult);
     }

     ////////////////////////////////////////////////////////////
	@Override
	public OrderResult findById(String resultId) {
		return orderResultDao.findById(resultId);
	}
}

