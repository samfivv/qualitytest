package com.midai.miya.item.dao;

import com.midai.miya.item.model.ItemAddtitional;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface ItemAddtitionalDao {

     List<ItemAddtitional> findByConditions(@Param("itemAddtitional")ItemAddtitional itemAddtitional,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("itemAddtitional")ItemAddtitional itemAddtitional);

     void save(@Param("itemAddtitional")ItemAddtitional itemAddtitional);

     void update(@Param("itemAddtitional")ItemAddtitional itemAddtitional);

     void delete(@Param("itemAddtitional")ItemAddtitional itemAddtitional);
     
     //按item_id删除 
     void deleteByItemId(@Param("ItemId")String ItemId);
 
}