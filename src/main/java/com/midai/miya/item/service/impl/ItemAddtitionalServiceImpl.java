package com.midai.miya.item.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.item.dao.ItemAddtitionalDao;
import com.midai.miya.item.service.ItemAddtitionalService;
import com.midai.miya.item.model.ItemAddtitional;
import com.midai.miya.utils.PageUtil;

@Service
public class ItemAddtitionalServiceImpl implements ItemAddtitionalService {

     @Autowired
     private ItemAddtitionalDao itemAddtitionalDao;

     @Override
     public List<ItemAddtitional> findByConditions(ItemAddtitional itemAddtitional,PageUtil page) {
        List<ItemAddtitional> lists=itemAddtitionalDao.findByConditions(itemAddtitional,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(ItemAddtitional itemAddtitional) {
        long count=itemAddtitionalDao.findByConditionsCount(itemAddtitional);
        return count;
     }

     @Override
     public void save(ItemAddtitional itemAddtitional) {
        itemAddtitionalDao.save(itemAddtitional);
     }

     @Override
     public void update(ItemAddtitional itemAddtitional) {
        itemAddtitionalDao.update(itemAddtitional);
     }

     @Override
     public void delete(ItemAddtitional itemAddtitional) {
        itemAddtitionalDao.delete(itemAddtitional);
     }
    ////////////////////////////////////////////////////////////////////////////////
	@Override
	public void deleteByItemId(String ItemId) {
		itemAddtitionalDao.deleteByItemId(ItemId);
	}

	@Override
	public List<ItemAddtitional> findItemAddtitionalAll(ItemAddtitional itemAddtitional,PageUtil page){  
	//public List<ItemAddtitional> findItemAddtitionalAll(ItemAddtitional itemAddtitional,PageUtil page) {
		List<ItemAddtitional> lists=itemAddtitionalDao.findItemAddtitionalAll(itemAddtitional,page);
        return lists;
	}

	@Override
	public long findItemAddtitionalAllCount(ItemAddtitional itemAddtitional) {
        long count=itemAddtitionalDao.findItemAddtitionalAllCount(itemAddtitional);
        return count;
	}

	@Override
	public ItemAddtitional findItemAddtitionalById(String addtitionalId) {
		ItemAddtitional itemAddtitional=itemAddtitionalDao.findItemAddtitionalById(addtitionalId);
		return itemAddtitional;
	}

}

