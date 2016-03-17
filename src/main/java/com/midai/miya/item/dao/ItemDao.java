package com.midai.miya.item.dao;

import com.midai.miya.item.model.Item;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface ItemDao {

     List<Item> findByConditions(@Param("item")Item item,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("item")Item item);

     void save(@Param("item")Item item);

     void update(@Param("item")Item item);

     void delete(@Param("item")Item item);
     
     //是否含有下级项目 
     int findCountItemByParentId(@Param("itemId") String itemId); 
     
     //是否含有附加项目 
     int findCountAddtitionalByItemId(@Param("itemId") String itemId); 
 
     List<Item> findItemAll();
     
     int findCountByItemName(@Param("itemName") String itemName,@Param("itemId") String itemId); 
     
     Item findItemById(@Param("itemId") String itemId);
     
     //查询第一级项目
     List<Item> findTopLevelItem();
     
}