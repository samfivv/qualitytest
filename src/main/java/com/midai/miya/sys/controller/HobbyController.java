package com.midai.miya.sys.controller;

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
import com.midai.miya.sys.model.Hobby;
import com.midai.miya.sys.service.HobbyService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/hobbyController")
public class HobbyController extends BaseController {

	private static final long serialVersionUID = 6135828177276192568L;
	@Autowired
	private HobbyService hobbyService;
	
	/**
	 * 爱好管理
	 * 查询爱好表
	 * 王梦圆
	 * 2015年4月27日
	 */
	@RequestMapping("/findAllHobby")
	public @ResponseBody Grid findAllHobby(Hobby hobby, HttpServletRequest request){
		long count=hobbyService.findCountHobby(hobby);
		List<Hobby> hobbys=hobbyService.findAllHobby(hobby,this.getPage(request));
		Grid grid=new Grid();
		grid.setRows(hobbys);
		grid.setTotal(count);
		return grid;
	}
	
	
	/**
	 * 添加爱好
	 * 王梦圆
	 * 2015年4月27日
	 */
	@RequestMapping("/addHobby")
	public @ResponseBody Result addHobby(Hobby hobby,HttpServletRequest request){
		this.addLog(request, "添加爱好", Constant.LOG_TYPE_ADD);
		Result result=new Result();
		if(hobby.getHobbyName()!=null){
			hobby.setHobbyName(hobby.getHobbyName().replace(" ", ""));
		}
		long hobb=hobbyService.findHobbyByName(hobby);
		if(hobb>=1){
			result.setMsg("名称不能重复");
			result.setSuccess(false);
		}else if(hobby.getHobbyName().trim().equals("")){
				result.setMsg("名称不能为空");
				result.setSuccess(false);
			}else{
				hobby.setHobbyId(UUIDUtil.getUUID());
				hobby.setHobbyCreateTime(new Date());
				result.setMsg("添加成功");
				result.setSuccess(true);
				hobbyService.addHobby(hobby);
			}
		 
		return result;
	}
	
	/**
	 * 根据id查看爱好信息
	 * 王梦圆
	 * 2015年4月28日
	 */
	@RequestMapping("/findHobbyById")
	public @ResponseBody Hobby findHobbyById(String hobbyId,HttpServletRequest request){
		Hobby hobby=hobbyService.findHobbyById(hobbyId);
		return hobby;
	}
	
	@RequestMapping("/updateHobby")
	public @ResponseBody Result updateHobby(Hobby hobby,HttpServletRequest request){
		this.addLog(request, "修改爱好", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		if(hobby.getHobbyName()!=null){
			hobby.setHobbyName(hobby.getHobbyName().replace(" ", ""));
		}
		long hobb=hobbyService.findHobbyByName(hobby);
		if(hobb>=1){
			result.setMsg("名称不能重复");
			result.setSuccess(false);
		}else if(hobby.getHobbyName().trim().equals("")){
				result.setMsg("名称不能为空");
				result.setSuccess(false);
			}else{
				hobbyService.updateHobby(hobby);
				result.setMsg("修改成功");
				result.setSuccess(true);
			}
		return result;
	}
	
	@RequestMapping("/deleteHobby")
	public @ResponseBody Result deleteHobby(String hobbyId,HttpServletRequest request){
		this.addLog(request, "删除爱好", Constant.LOG_TYPE_DELETE);
		Hobby hobby=hobbyService.findHobbyById(hobbyId);
		hobby.setHobbyState(2);
		hobbyService.updateHobby(hobby);
		Result result=new Result();
		result.setMsg("删除成功");
		result.setSuccess(true);
		return result;
	}
}
