package com.midai.miya.order.dao;

import com.midai.miya.order.model.OrderAddtitionalItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface OrderAddtitionalItemDao {

     List<OrderAddtitionalItem> findByConditions(@Param("orderAddtitionalItem")OrderAddtitionalItem orderAddtitionalItem,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("orderAddtitionalItem")OrderAddtitionalItem orderAddtitionalItem);

     void save(@Param("orderAddtitionalItem")OrderAddtitionalItem orderAddtitionalItem);

     void update(@Param("orderAddtitionalItem")OrderAddtitionalItem orderAddtitionalItem);

     void delete(@Param("orderAddtitionalItem")OrderAddtitionalItem orderAddtitionalItem);

}