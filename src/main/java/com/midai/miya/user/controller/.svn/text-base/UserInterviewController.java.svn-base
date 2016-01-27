package com.midai.miya.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.model.UserInterview;
import com.midai.miya.user.service.UserInterviewService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/userInterview")
public class UserInterviewController extends BaseController {

 
	private static final long serialVersionUID = -97473157544092195L;
	
	@Autowired
	private UserInterviewService userInterviewService;
	
	@RequestMapping("/findAllUserInterview")
	public @ResponseBody Grid findAllUserInterview(HttpServletRequest request,UserInterview userInterview){
		Grid grid=new Grid();
		List<UserInterview> userInterviews=userInterviewService.findByConditions(userInterview, this.getPage(request));
		long count=userInterviewService.findByConditionsCount(userInterview);
		grid.setRows(userInterviews);
		grid.setTotal(count);
		return grid;
	}
	
	@RequestMapping("/saveUserInterview")
	public @ResponseBody Result saveUserInterview(HttpServletRequest request,UserInterview userInterview){
		Result result=new Result();
		if("".equals(userInterview.getUserInterviewTitle().replaceAll(" ", ""))){
			result.setSuccess(false);
			result.setMsg("标题不能为空");
			return result;
		}
		int count=userInterviewService.findUserInterviewCountBytitle(userInterview.getUserInterviewTitle(), userInterview.getUserInterviewId());
		if(count>0){
			result.setSuccess(false);
			result.setMsg("标题不能重复");
			return result;
		}
		if("".equals(userInterview.getUserInterviewAbstract().replaceAll(" ", ""))){
			result.setSuccess(false);
			result.setMsg("简介不能为空");
			return result;
		}
		userInterview.setUserInterviewId(UUIDUtil.getUUID());
		userInterview.setCreateOperatorId(this.getCurrentOperator(request).getOperatorId());
		userInterview.setUpdateOperatorId(this.getCurrentOperator(request).getOperatorId());
		userInterview.setCreateTime(new Date());
		userInterview.setUpdateTime(new Date());
		userInterviewService.save(userInterview);
		result.setSuccess(true);
		result.setMsg("保存成功");
		return result;
	}
	
	@RequestMapping("/updateUserInterview")
	public @ResponseBody Result updateUserInterview(HttpServletRequest request,UserInterview userInterview){
		Result result=new Result();
		if("".equals(userInterview.getUserInterviewTitle().replaceAll(" ", ""))){
			result.setSuccess(false);
			result.setMsg("标题不能为空");
			return result;
		}
		int count=userInterviewService.findUserInterviewCountBytitle(userInterview.getUserInterviewTitle(), userInterview.getUserInterviewId());
		if(count>0){
			result.setSuccess(false);
			result.setMsg("标题不能重复");
			return result;
		}
		if("".equals(userInterview.getUserInterviewAbstract().replaceAll(" ", ""))){
			result.setSuccess(false);
			result.setMsg("简介不能为空");
			return result;
		}
		userInterview.setUpdateOperatorId(this.getCurrentOperator(request).getOperatorId());
		userInterview.setUpdateTime(new Date());
		userInterviewService.update(userInterview);
		result.setSuccess(true);
		result.setMsg("修改成功");
		return result;
	}
	
	@RequestMapping("/findUserInterviewById")
	public String findUserInterviewById(HttpServletRequest request,String userInterviewId){
		UserInterview userInterview=userInterviewService.findUserInterviewById(userInterviewId);
		request.setAttribute("userInterview", userInterview);
		return "securityJsp/application/userInterviewForm";
	}
	
	@RequestMapping("/deleteUserInterview")
	public @ResponseBody Result deleteUserInterview(HttpServletRequest request,UserInterview userInterview){
		Result result= new Result();
		userInterview.setUserInterviewState(3);
		userInterviewService.update(userInterview);
		result.setSuccess(true);
		result.setMsg("删除成功");
		return result;
	}
}
