package com.midai.miya.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.item.dao.ItemTypeDao;
import com.midai.miya.item.service.ItemTypeService;
import com.midai.miya.item.model.Item;
import com.midai.miya.item.model.ItemType;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.Tree;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {

     @Autowired
     private ItemTypeDao itemTypeDao;

     @Override
     public List<ItemType> findByConditions(ItemType itemType,PageUtil page) {
        List<ItemType> lists=itemTypeDao.findByConditions(itemType,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(ItemType itemType) {
        long count=itemTypeDao.findByConditionsCount(itemType);
        return count;
     }

     @Override
     public void save(ItemType itemType) {
        itemTypeDao.save(itemType);
     }

     @Override
     public void update(ItemType itemType) {
        itemTypeDao.update(itemType);
     }

     @Override
     public void delete(ItemType itemType) {
        itemTypeDao.delete(itemType);
     }

    //
	@Override
	public int findItemTypeCountById(String typeId) {
        int count=itemTypeDao.findItemTypeCountById(typeId);
        return count;
	}

	@Override
	public int findItemTypeCountByName(String typeName) {
		int count=itemTypeDao.findItemTypeCountById(typeName);
		return count;
	}

	@Override
	public ItemType findItemTypeById(String typeId) {
		return itemTypeDao.findItemTypeById(typeId);
	}

	@Override
	public List<Tree> findItemForOption() {
		List<ItemType> itemTypes = itemTypeDao.findItemForOption();
		List<Tree> trees = new ArrayList<Tree>();
		for (ItemType itemType : itemTypes) {
			Tree node = new Tree();
			node.setText(itemType.getTypeName());
			node.setId(itemType.getTypeId());
			node.setPid(itemType.getTypeId());
			trees.add(node);
		}
		return trees;
	}
}

