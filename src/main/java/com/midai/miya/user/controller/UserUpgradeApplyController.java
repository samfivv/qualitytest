package com.midai.miya.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.model.User;
import com.midai.miya.user.model.UserUpgradeApply;
import com.midai.miya.user.service.UserUpgradeApplyService;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/userUpgradeApply")
public class UserUpgradeApplyController extends BaseController {
 
	private static final long serialVersionUID = -8227717630509661249L;
	@Autowired
	private UserUpgradeApplyService userUpgradeApplyService;
	
	@RequestMapping("/findAllUserUpgradeApply")
	public @ResponseBody Grid findAllUserUpgradeApply(HttpServletRequest request,UserUpgradeApply userUpgradeApply){
		Grid grid=new Grid();
		if(userUpgradeApply.getAuditState()==null){
			userUpgradeApply.setAuditState(1);
		}
		List<UserUpgradeApply> userUpgradeApplys=userUpgradeApplyService.findByConditions(userUpgradeApply, this.getPage(request));
		long count=userUpgradeApplyService.findByConditionsCount(userUpgradeApply);
		grid.setRows(userUpgradeApplys);
		grid.setTotal(count);
		return grid;
	}
	@RequestMapping("/findUserUpgradeApplyById")
	public @ResponseBody UserUpgradeApply findUserUpgradeApplyById(HttpServletRequest request,String userUpgradeApplyId){
		UserUpgradeApply userUpgradeApply=userUpgradeApplyService.findUpgradeApplyById(userUpgradeApplyId);
		return userUpgradeApply;
	}
	@RequestMapping("/update")
	public @ResponseBody Result update(HttpServletRequest request,UserUpgradeApply userUpgradeApply){
		Result result=new Result();
		if(userUpgradeApply.getAuditState()==3){
			if("".equals(userUpgradeApply.getApplyNotPassReason().replace(" ", ""))){
				result.setSuccess(false);
				result.setMsg("不通过原因不能为空");
				return result;
			}
		}
		if(userUpgradeApply.getAuditState()==2){
			userUpgradeApply.setApplyNotPassReason("");
		}
		userUpgradeApply.setAuditTime(new Date());
		userUpgradeApply.setOperatorId(this.getCurrentOperator(request).getOperatorId());
		userUpgradeApplyService.update(userUpgradeApply);
		result.setSuccess(true);
		result.setMsg("审核成功");
		this.addLog(request, "审核特长人升级申请", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	@RequestMapping(value = "/exportUserUpgradeApply" , method = RequestMethod.POST)
	public void exportUserUpgradeApply(UserUpgradeApply userUpgradeApply,HttpServletRequest request, HttpServletResponse response){
		this.addLog(request, "特长人升级申请", Constant.LOG_TYPE_EXPORT);
		List<UserUpgradeApply> userUpgradeApplys=userUpgradeApplyService.findByConditions(userUpgradeApply, this.getExportPage(request));
		 super.doExport(request, response, userUpgradeApplys,"特长人升级申请","特长人升级申请",this.getHeadForUserUpgradeApply(), this.getColumnForUserUpgradeApply());
	}
	private String[] getHeadForUserUpgradeApply(){
		         return new String[]{
		        		 "申请人昵称","申请人手机","申请类型","申请时间","审核人","审核状态","不通过原因","审核时间"
		};
	}
	private String[] getColumnForUserUpgradeApply(){
		return new String[]{
				"userNickname","userPhone","userUpgradeApplyTypeStr","createTimeStr","operatorName","auditStateStr","applyNotPassReason","auditTimeStr"
		};
	}

}
