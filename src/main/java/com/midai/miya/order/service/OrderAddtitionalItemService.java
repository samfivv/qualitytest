package com.midai.miya.order.service;

import com.midai.miya.order.model.OrderAddtitionalItem;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface OrderAddtitionalItemService {

     List<OrderAddtitionalItem> findByConditions(OrderAddtitionalItem orderAddtitionalItem,PageUtil page);

     long findByConditionsCount(OrderAddtitionalItem orderAddtitionalItem);

     void save(OrderAddtitionalItem orderAddtitionalItem);

     void update(OrderAddtitionalItem orderAddtitionalItem);

     void delete(OrderAddtitionalItem orderAddtitionalItem);

}