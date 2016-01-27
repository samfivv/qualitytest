package com.midai.miya.user.controller;

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
import com.midai.miya.user.model.UserBillChargeback;
import com.midai.miya.user.service.UserBillChargebackService;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/userBillChangeBack")
public class UserBillChargebackController extends BaseController {
 
	private static final long serialVersionUID = -192313754775152793L;
	@Autowired
	private UserBillChargebackService userBillChargebackService;
	
	@RequestMapping("/findAllUserBillChargeback")
	public @ResponseBody Grid findAllUserBillChargeback(UserBillChargeback userBillChargeback,HttpServletRequest request){
		Grid grid=new Grid();
		List<UserBillChargeback> userBillChargebacks=userBillChargebackService.findByConditions(userBillChargeback, this.getPage(request));
		long count=userBillChargebackService.findByConditionsCount(userBillChargeback);
		grid.setRows(userBillChargebacks);
		grid.setTotal(count);
		return grid;
	}
	@RequestMapping("/findBillChargebackById")
	public @ResponseBody UserBillChargeback findBillChargebackById(String userBillChargebackId,HttpServletRequest request){
		UserBillChargeback userBillChargeback=userBillChargebackService.findBillChargebackByUserId(userBillChargebackId);
		this.addLog(request, "查看退款申请详情", Constant.LOG_TYPE_SELECT);
		return userBillChargeback;
	}
	@RequestMapping("/updateUserBillChargeback")
	public @ResponseBody Result updateUserBillChargeback(UserBillChargeback userBillChargeback,HttpServletRequest request){
		Result result=new Result();
		if(userBillChargeback.getUserBillChargebackState()==3){
			if("".equals(userBillChargeback.getNotPassReason().replace(" ", ""))){
				result.setSuccess(false);
				result.setMsg("不通过原因不能为空");
				return result;
			}
		}
		UserBillChargeback userBillChargebacks=userBillChargebackService.findBillChargebackByUserId(userBillChargeback.getUserBillChargebackId());
		userBillChargebacks.setUserBillChargebackState(userBillChargeback.getUserBillChargebackState());
		userBillChargebacks.setOperatorName(this.getCurrentOperator(request).getOperatorName());
		userBillChargebacks.setOperatorId(this.getCurrentOperator(request).getOperatorId());
		userBillChargebacks.setNotPassReason(userBillChargeback.getNotPassReason());
		userBillChargebackService.update(userBillChargebacks);
		result.setSuccess(true);
		result.setMsg("修改成功");
		this.addLog(request, "退款审核", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	@RequestMapping(value = "/exportUserBillChargeback" , method = RequestMethod.POST)
	public void exportUserBillChargeback(UserBillChargeback userBillChargeback,HttpServletRequest request, HttpServletResponse response){
		this.addLog(request, "退款申请", Constant.LOG_TYPE_EXPORT);
		List<UserBillChargeback> userBillChargebacks=userBillChargebackService.findByConditions(userBillChargeback,this.getExportPage(request));
		 super.doExport(request, response, userBillChargebacks,"退款申请","退款申请",this.getHeadForUserBillChargeback(), this.getColumnForUserBillChargeback());
	}
	private String[] getHeadForUserBillChargeback(){
		         return new String[]{
		        		 "订单号","申请人","申请人手机","创建时间","被约人","被约人手机","订单状态","审核人","审核状态","不通过原因","审核时间"
		};
	}
	private String[] getColumnForUserBillChargeback(){
		return new String[]{
				"billNum","fromCall","fromPhoneNumber","createTimeStr","userName","userPhone","billStateStr","operatorName","userBillChargebackStateStr","notPassReason","approvalTimeStr"
		};
	}

}
