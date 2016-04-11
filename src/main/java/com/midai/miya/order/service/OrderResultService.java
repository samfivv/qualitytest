package com.midai.miya.order.service;

import com.midai.miya.order.model.OrderResult;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface OrderResultService {

     List<OrderResult> findByConditions(OrderResult orderResult,PageUtil page);

     long findByConditionsCount(OrderResult orderResult);

     void save(OrderResult orderResult);

     void update(OrderResult orderResult);

     void delete(OrderResult orderResult);

     ////////////////////////////////////////////////////////////////
     OrderResult findById(String resultId);
}