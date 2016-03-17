package com.midai.miya.item.dao;

import com.midai.miya.item.model.ItemType;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface ItemTypeDao {

     List<ItemType> findByConditions(@Param("itemType")ItemType itemType,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("itemType")ItemType itemType);

     void save(@Param("itemType")ItemType itemType);

     void update(@Param("itemType")ItemType itemType);

     void delete(@Param("itemType")ItemType itemType);
     
     //
   	 int findItemTypeCountById(@Param("typeId")String typeId);

   	 int findItemTypeCountByName(@Param("typeName")String typeName);
   	 ItemType findItemTypeById(@Param("typeId")String typeId);
     
     //为下拉选项准备数据用/
   	List<ItemType>  findItemForOption();
}