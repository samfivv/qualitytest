package com.midai.miya.item.service;

import com.midai.miya.item.model.ItemAddtitional;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface ItemAddtitionalService {

     List<ItemAddtitional> findByConditions(ItemAddtitional itemAddtitional,PageUtil page);

     long findByConditionsCount(ItemAddtitional itemAddtitional);

     void save(ItemAddtitional itemAddtitional);

     void update(ItemAddtitional itemAddtitional);

     void delete(ItemAddtitional itemAddtitional);
     
     //按item_id删除 
     void deleteByItemId(String ItemId);

     List<ItemAddtitional> findItemAddtitionalAll(ItemAddtitional itemAddtitional,PageUtil page);  
     long findItemAddtitionalAllCount(ItemAddtitional itemAddtitional);
     ItemAddtitional findItemAddtitionalById(String addtitionalId);
}