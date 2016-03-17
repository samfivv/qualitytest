package com.midai.miya.item.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.item.dao.ItemAddtitionalDao;
import com.midai.miya.item.dao.ItemDao;
import com.midai.miya.item.service.ItemService;
import com.midai.miya.item.model.Item;
import com.midai.miya.sys.model.Permission;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.Tree;

@Service
public class ItemServiceImpl implements ItemService {

     @Autowired
     private ItemDao itemDao;

     @Override
     public List<Item> findByConditions(Item item,PageUtil page) {
        List<Item> lists=itemDao.findByConditions(item,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Item item) {
        long count=itemDao.findByConditionsCount(item);
        return count;
     }

     @Override
     public void save(Item item) {
        itemDao.save(item);
     }

     @Override
     public void update(Item item) {
        itemDao.update(item);
     }
 	
    @Override
 	public void delete(Item item) {
 		itemDao.delete(item);
 	}
 	
   //是否含有下级项目 
	@Override
	public int findCountItemByParentId(String itemId) {
		int count = itemDao.findCountItemByParentId(itemId);
		return count;
	}
	//是否含有附加项目
	@Override
	public int findCountAddtitionalByItemId(String itemId) {
		int count = itemDao.findCountAddtitionalByItemId(itemId);
		return count;
	}

	@Override
	public List<Item> findItemAll() {
		List<Item> items = itemDao.findItemAll();
		return items;
	}

	@Override
	public int findCountByItemName(String itemName,String itemId) {
		int count = itemDao.findCountByItemName(itemName,itemId);
		return 0;
	}

	@Override
	public Item findItemById(String itemId) {
		Item item = itemDao.findItemById(itemId);
		return item;
	}

	//查询第一级项目
	@Override
	public List<Tree> findTopLevelItem() {
		List<Item> items = itemDao.findTopLevelItem();
		List<Tree> trees = new ArrayList<Tree>();
		for (Item item : items) {
			Tree node = new Tree();
			node.setText(item.getItemName());
			node.setId(item.getItemId());
			node.setPid(item.getParentId());
			trees.add(node);
		}
		return trees;

	}

}

