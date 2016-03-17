package com.midai.miya.item.service;

import com.midai.miya.item.model.ItemType;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.Tree;

public interface ItemTypeService {

     List<ItemType> findByConditions(ItemType itemType,PageUtil page);

     long findByConditionsCount(ItemType itemType);

     void save(ItemType itemType);

     void update(ItemType itemType);

     void delete(ItemType itemType);

     //
   	 int findItemTypeCountById(String typeId);

   	 int findItemTypeCountByName(String typeName);
     ItemType findItemTypeById(String typeId);
     
     //为下拉选项准备数据用/
   	 List<Tree>  findItemForOption();     
}