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
import com.midai.miya.easyui.Grid;
import com.midai.miya.item.model.ItemType;
import com.midai.miya.item.service.ItemTypeService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.Tree;


@Controller
@RequestMapping("/itemtype")
public class ItemTypeController extends BaseController {
	private static final long serialVersionUID = -3386527237149558723L;
	
	@Autowired
	private ItemTypeService itemTypeService;
	
	@RequestMapping("/findItemTypeAll")
	public @ResponseBody Grid findItemTypeAll(HttpServletRequest request,ItemType itemtype){
		Grid grid=new Grid();
		List<ItemType> lists=itemTypeService.findByConditions(itemtype, this.getPage(request));
		long count=itemTypeService.findByConditionsCount(itemtype);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}	
	
    @RequestMapping("/saveItemType")
	public @ResponseBody Result saveItemType(ItemType itemType,HttpServletRequest request){
		Result result=new Result();
		if("".equals(itemType.getTypeId().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("类型编号不能为空格");
			return result;
		}
		int numCount=itemTypeService.findItemTypeCountById(itemType.getTypeId());
		if(numCount>0){
			result.setSuccess(false);
			result.setMsg("类型编号不能重复");
			return result;
		}
		if("".equals(itemType.getTypeName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("类型名称不能为空格");
			return result;
		}
		int nameCount=itemTypeService.findItemTypeCountByName(itemType.getTypeName());
		if(nameCount>0){
			result.setSuccess(false);
			result.setMsg("类型名称不能重复");
			return result;
		}
		itemType.setCreateTime(new Date());
		itemTypeService.save(itemType);
		result.setSuccess(true);
		result.setMsg("添加成功");
		this.addLog(request, "添加项目类型信息", Constant.LOG_TYPE_ADD);
		return result;
	}	
    
    @RequestMapping("/updateItemType")
    public @ResponseBody Result updateItemType(ItemType itemType,HttpServletRequest request){
    	Result result=new Result();
		if("".equals(itemType.getTypeName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("类型名称不能为空格");
			return result;
		}
		/*
		int nameCount=itemTypeService.findItemTypeCountByName(itemType.getTypeName());
		if(nameCount>0){
			result.setSuccess(false);
			result.setMsg("类型名称不能重复");
			return result;
		}
		*/
		itemTypeService.update(itemType);
    	result.setSuccess(true);
    	result.setMsg("修改成功");
    	this.addLog(request, "修改项目类型信息", Constant.LOG_TYPE_UPDATE);
    	return result;
    }
    
    @RequestMapping("/findItemTypeById")
    public @ResponseBody ItemType findItemTypeById(HttpServletRequest request,String typeId){
    	ItemType school=itemTypeService.findItemTypeById(typeId);
    	this.addLog(request, "查看项目类型详情", Constant.LOG_TYPE_SELECT);
    	return school;
    }  
    
    @RequestMapping("/deleteItemTypeById")
    public @ResponseBody Result deleteItemTypeById(HttpServletRequest request,String typeId){
    	Result result=new Result();
    	ItemType itemType=new ItemType();
    	itemType.setTypeId(typeId);
    	itemTypeService.delete(itemType);
    	result.setSuccess(true);
    	result.setMsg("删除成功");
    	this.addLog(request, "删除项目类型", Constant.LOG_TYPE_DELETE);
    	return result;
    }  
    
	@RequestMapping("/findItemForOption")
	public @ResponseBody List<Tree> findItemForOption(HttpServletRequest request){
		//Grid grid=new Grid();
		List<Tree> lists=itemTypeService.findItemForOption();
		//long count=itemTypeService.findByConditionsCount(itemtype);
		//grid.setRows(lists);
		//grid.setTotal(count);
		return lists;
	}	

}
