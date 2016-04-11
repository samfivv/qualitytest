package com.midai.miya.user.controller;

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
import com.midai.miya.user.model.ApprovalUser;
import com.midai.miya.user.model.User;
import com.midai.miya.user.service.ApprovalUserService;
import com.midai.miya.user.service.UserService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.PageUtil;

@Controller
@RequestMapping("/approvaluser")
public class ApprovalUserController extends BaseController {
	 
	private static final long serialVersionUID = -7968523323054952398L;
	@Autowired
	private ApprovalUserService approvalUserService;
	@Autowired
	private UserService userService;

	    
    @RequestMapping("/findAllApprovalUser")
    public @ResponseBody Grid findAllApprovalUser(ApprovalUser approvalUser,HttpServletRequest request){
    	this.addLog(request, "查询待认证的会员", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	PageUtil page = this.getPage(request);
    	if (page.getOrder() == null || "".equals(page.getOrder().toString())){
    		page.setOrder("approvalTime");
    		page.setSort("DESC");
    	}
    	List<ApprovalUser> approvalUsers=approvalUserService.findByConditions(approvalUser,page);
    	long count=approvalUserService.findByConditionsCount(approvalUser);
    	grid.setRows(approvalUsers);
    	grid.setTotal(count);
    	return grid;
    }
    
    @RequestMapping("/findById")
    public @ResponseBody ApprovalUser findById(String approvalUserId,HttpServletRequest request){
    	ApprovalUser approvalUsers=approvalUserService.findById(approvalUserId);
    	return approvalUsers;
    }
    
	@RequestMapping("/update")
	public @ResponseBody Result update(HttpServletRequest request,ApprovalUser approvalUser){
		this.addLog(request, "审批认证会员", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		approvalUser.setApprovalTime(new Date());
		approvalUserService.update(approvalUser);
		
		User user = new User();
		user.setUserId(approvalUser.getUserId());
		if (approvalUser.getApprovalState() == 1)
			user.setUserState(2);
		else
			user.setUserState(1);
		userService.update(user);
		result.setSuccess(true);
		result.setMsg("审批成功");
		return result;
	}     
}
