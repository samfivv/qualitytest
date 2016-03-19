package com.midai.miya.item.service;

import com.midai.miya.item.model.Item;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.Tree;

public interface ItemService {

     List<Item> findByConditions(Item item,PageUtil page);

     long findByConditionsCount(Item item);

     void save(Item item);

     void update(Item item);

     void delete(Item item);
     
     //是否含有下级项目 
     int findCountItemByParentId(String itemId); 
     
     //是否含有附加项目 
     int findCountAddtitionalByItemId(String itemId); 

     List<Item> findItemAll();
     
     int findCountByItemName(String itemName,String itemId); 
     
     Item findItemById(String itemId);
     
     //查询第一级项目
     List<Tree> findTopLevelItem();    
     
     //查询所有项目返回Tree
     List<Tree> findAllItemTree();      
}