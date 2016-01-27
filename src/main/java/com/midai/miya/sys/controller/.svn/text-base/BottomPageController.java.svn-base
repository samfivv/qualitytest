package com.midai.miya.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.sys.model.BottomPage;
import com.midai.miya.sys.service.BottomPageService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/bottomPage")
public class BottomPageController extends BaseController {

	private static final long serialVersionUID = -6396042977075055909L;
	
	@Autowired
	private BottomPageService bottomPageService;
	
	@RequestMapping("/findAllBottomPage")
	public @ResponseBody Grid findAllBottomPage(HttpServletRequest request,BottomPage bottomPage){
		Grid grid=new Grid();
		List<BottomPage> bottomPages=bottomPageService.findByConditions(bottomPage, this.getPage(request));
		long count=bottomPageService.findByConditionsCount(bottomPage);
		grid.setRows(bottomPages);
		grid.setTotal(count);
		return grid;
	}
	
	@RequestMapping("/saveBottomPage")
	public @ResponseBody Result saveBottomPage(HttpServletRequest request,BottomPage bottomPage){
		Result result=new Result();
		int count=bottomPageService.findBottomPageCountByName(bottomPage);
		if(count>0){
			result.setSuccess(false);
			result.setMsg("标题重复");
			return result;
		}
		bottomPage.setBottomPageId(UUIDUtil.getUUID());
		bottomPage.setCreateTime(new Date());
		bottomPageService.save(bottomPage);
		result.setSuccess(true);
		result.setMsg("保存成功");
		return result;
	}
	
	@RequestMapping("/updateBottomPage")
	public @ResponseBody Result updateBottomPage(HttpServletRequest request,BottomPage bottomPage){
		Result result=new Result();
		int count=bottomPageService.findBottomPageCountByName(bottomPage);
		if(count>0){
			result.setSuccess(false);
			result.setMsg("标题重复");
			return result;
		}
		bottomPageService.update(bottomPage);
		result.setSuccess(true);
		result.setMsg("修改成功");
		return result;
	}
	
	@RequestMapping("/findBottomPageById")
	public String findBottomPageById(HttpServletRequest request,String bottomPageId){
		BottomPage bottomPage=bottomPageService.findBottomPageById(bottomPageId);
		request.setAttribute("bottomPage", bottomPage);
		return "securityJsp/bottomPage/bottomPageForm";
	}
	
	@RequestMapping("/deleteBottomPageById")
	public @ResponseBody Result deleteBottomPageById(HttpServletRequest request,BottomPage bottomPage){
		Result result=new Result();
		bottomPageService.delete(bottomPage);
		result.setSuccess(true);
		result.setMsg("删除成功");
		return result;
	}
	

}
