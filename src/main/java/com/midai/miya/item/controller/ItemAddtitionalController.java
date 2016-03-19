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
import com.midai.miya.item.model.Item;
import com.midai.miya.item.model.ItemAddtitional;
import com.midai.miya.item.model.ItemType;
import com.midai.miya.item.service.ItemAddtitionalService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/itemaddtitional")
public class ItemAddtitionalController extends BaseController{
private static final long serialVersionUID = -3386527237149558723L;
	
	@Autowired
	private ItemAddtitionalService itemAddtitionalService;

	@RequestMapping("/findItemAddtitionalAll")
	public @ResponseBody Grid findItemAddtitionalAll(HttpServletRequest request,ItemAddtitional itemAddtitional){
		Grid grid=new Grid();
		List<ItemAddtitional> lists=itemAddtitionalService.findItemAddtitionalAll(itemAddtitional, this.getPage(request));
		long count=itemAddtitionalService.findItemAddtitionalAllCount(itemAddtitional);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}
	
    @RequestMapping("/saveItemAddtitional")
	public @ResponseBody Result saveItemAddtitional(ItemAddtitional itemAddtitional,HttpServletRequest request){
		Result result=new Result();
		if("".equals(itemAddtitional.getAddtitionalName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("描述不能为空格");
			return result;
		}
		if("".equals(itemAddtitional.getItemId().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("项目不能为空格");
			return result;
		}
		itemAddtitional.setAddtitionalId(UUIDUtil.getUUID());
		itemAddtitional.setCreateTime(new Date());
		if (itemAddtitional.getAddtitionalPrice() == null)
		{
			itemAddtitional.setAddtitionalPrice(0.0);
		}
		itemAddtitionalService.save(itemAddtitional);
		result.setSuccess(true);
		result.setMsg("添加成功");
		this.addLog(request, "添加附加项目信息", Constant.LOG_TYPE_ADD);
		return result;
	}
    
    @RequestMapping("/updateItemAddtitional")
 	public @ResponseBody Result updateItemAddtitional(ItemAddtitional itemAddtitional,HttpServletRequest request){
 		Result result=new Result();
 		if("".equals(itemAddtitional.getAddtitionalName().replace(" ", ""))){
 			result.setSuccess(false);
 			result.setMsg("描述不能为空格");
 			return result;
 		}
 		if("".equals(itemAddtitional.getItemId().replace(" ", ""))){
 			result.setSuccess(false);
 			result.setMsg("项目不能为空格");
 			return result;
 		}
 		if (itemAddtitional.getAddtitionalPrice() == null)
 		{
 			itemAddtitional.setAddtitionalPrice(0.0);
 		}
 		itemAddtitionalService.update(itemAddtitional); 
 		result.setSuccess(true);
 		result.setMsg("修改成功");
 		this.addLog(request, "修改附加项目信息", Constant.LOG_TYPE_ADD);
 		return result;
 	}  
    
	//删除
	@RequestMapping("/deleteItemAddtitional")
	@ResponseBody
	public Result deleteItemAddtitional(String addtitionalId,HttpServletRequest request) {
    	Result result=new Result();

    	ItemAddtitional itemAddtitional=new ItemAddtitional();
    	itemAddtitional.setAddtitionalId(addtitionalId);
    	itemAddtitionalService.delete(itemAddtitional);
    	result.setSuccess(true);
    	result.setMsg("删除成功");
    	this.addLog(request, "删除附加项目", Constant.LOG_TYPE_DELETE);
    	return result;
	}    
    
    @RequestMapping("/findItemAddtitionalById")
    public @ResponseBody ItemAddtitional findItemTypeById(HttpServletRequest request,String addtitionalId){
    	ItemAddtitional itemAddtitional =itemAddtitionalService.findItemAddtitionalById(addtitionalId);
    	this.addLog(request, "查看附加项目详情", Constant.LOG_TYPE_SELECT);
    	return itemAddtitional;
    } 	
}
