package com.midai.miya.item.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.item.model.Item;
import com.midai.miya.item.model.ItemType;
import com.midai.miya.item.service.ItemService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.Tree;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController{
	private static final long serialVersionUID = -3386527237149558723L;
	
	@Autowired
	private ItemService itemService;

	//删除
	@RequestMapping("/deleteItem")
	@ResponseBody
	public Result deleteItem(String itemId,HttpServletRequest request) {
    	Result result=new Result();
    	int count = itemService.findCountItemByParentId(itemId);
    	if (count != 0){
    	   	result.setSuccess(false);
        	result.setMsg("此项目下含有子项目,不能删除");
        	return result;
    	}
    	count = itemService.findCountAddtitionalByItemId(itemId);
    	if (count != 0){
    	   	result.setSuccess(false);
        	result.setMsg("此项目下含有附加项目,不能删除");
        	return result;
    	}
    	Item item=new Item();
    	item.setItemId(itemId);
    	itemService.delete(item);
    	result.setSuccess(true);
    	result.setMsg("删除成功");
    	this.addLog(request, "删除项目", Constant.LOG_TYPE_DELETE);
    	return result;
	}
	
	//查询所有数据
	@RequestMapping("/findItemAll")
	public @ResponseBody List<Item> findItemAll(HttpServletRequest request) {
		return itemService.findItemAll();
		//return tree;
	}
	
	//新增
    @RequestMapping("/saveItem")
	public @ResponseBody Result saveItem(Item item,HttpServletRequest request){
		Result result=new Result();

		if("".equals(item.getItemName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("项目名称不能为空格");
			return result;
		}
		int nameCount=itemService.findCountByItemName(item.getItemName(),"");
		if(nameCount>0){
			result.setSuccess(false);
			result.setMsg("项目名称不能重复");
			return result;
		}
		item.setItemId(UUIDUtil.getUUID());
		item.setCreateTime(new Date());
		itemService.save(item);
		result.setSuccess(true);
		result.setMsg("添加成功");
		this.addLog(request, "添加项目信息", Constant.LOG_TYPE_ADD);
		return result;
	}	
    
    //编辑
    @RequestMapping("/updateItem")
    public @ResponseBody Result updateItem(Item item,HttpServletRequest request){
    	Result result=new Result();
		if("".equals(item.getItemName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("项目名称不能为空格");
			return result;
		}
		int nameCount=itemService.findCountByItemName(item.getItemName(),item.getItemId());
		if(nameCount>0){
			result.setSuccess(false);
			result.setMsg("项目名称不能重复");
			return result;
		}
		
		if (item.getItemId() == item.getParentId())
		{
			result.setSuccess(false);
			result.setMsg("上级项目错误");
			return result;
		}
		itemService.update(item);
    	result.setSuccess(true);
    	result.setMsg("修改成功");
    	this.addLog(request, "修改项目信息", Constant.LOG_TYPE_UPDATE);
    	return result;
    }
    
	//查询所有数据
	@RequestMapping("/findItemById")
	public @ResponseBody Item findItemById(String itemId,HttpServletRequest request) {
		return itemService.findItemById(itemId);
		//return tree;
	}
    
	//查询第一级项目
	@RequestMapping("/findTopLevelItem")
	public @ResponseBody List<Tree> findTopLevelItem(HttpServletRequest request) {
		return itemService.findTopLevelItem();
		//return tree;
	}
 	
	////查询所有项目返回Tree
	@RequestMapping("/findAllItemTree")
	public @ResponseBody List<Tree> findAllItemTree(HttpServletRequest request) {
		return itemService.findAllItemTree();
	}
	
}
