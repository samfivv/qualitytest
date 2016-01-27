package com.midai.miya.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.NewParentTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.model.UserMoneyRmbLog;
import com.midai.miya.user.model.UserOftenChangeInfo;
import com.midai.miya.user.model.UserWithdrawDeposit;
import com.midai.miya.user.service.UserMoneyRmbLogService;
import com.midai.miya.user.service.UserOftenChangeInfoService;
import com.midai.miya.user.service.UserWithdrawDepositService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.NumberUtil;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/withdrawDeposit")
public class UserWithdrawDepositController extends BaseController {
 
	private static final long serialVersionUID = 5966483010951801834L;
	@Autowired
	private UserWithdrawDepositService userWithdrawDepositService;
	
	@RequestMapping("/findWithdrawDepositAll")
	public @ResponseBody Grid findWithdrawDepositAll(HttpServletRequest request,UserWithdrawDeposit userWithdrawDeposit){
		Grid grid=new Grid();
		if(userWithdrawDeposit.getDealState()==null){
			userWithdrawDeposit.setDealState(1);
		}
		List<UserWithdrawDeposit> userWithdrawDeposits=userWithdrawDepositService.findByConditions(userWithdrawDeposit, this.getPage(request));
		long count=userWithdrawDepositService.findByConditionsCount(userWithdrawDeposit);
		grid.setRows(userWithdrawDeposits);
		grid.setTotal(count);
		return grid;
	}
	
	@RequestMapping("/findWithdrawDepositById")
	public @ResponseBody UserWithdrawDeposit findWithdrawDepositById(HttpServletRequest request,String withdrawDepositId){
		UserWithdrawDeposit userWithdrawDeposit=userWithdrawDepositService.findUserWithdrawDepositById(withdrawDepositId);
		return userWithdrawDeposit;
	}
	
	@RequestMapping("/updateWithdrawDeposit")
	public @ResponseBody Result updateWithdrawDeposit(HttpServletRequest request,UserWithdrawDeposit userWithdrawDeposit){
		Result result=new Result();
		String remak=userWithdrawDeposit.getRemak();
		if(remak!=null){
			if("".equals(remak.replaceAll(" ", ""))){
				result.setSuccess(false);
				result.setMsg("不能为空格");
				return result;
			}
		}
		userWithdrawDeposit.setDealOperatorId(this.getCurrentOperator(request).getOperatorId());
		userWithdrawDeposit.setDealTime(new Date());
		userWithdrawDepositService.update(userWithdrawDeposit);
		boolean flag=sendMsg(request, userWithdrawDeposit.getPhoneNum(), userWithdrawDeposit.getRemak());
		if(flag){
			result.setMsg("处理成功并且发送短信成功");
		}else{
			result.setMsg("处理成功但是发送短信失败");
		}
		sendMail("提现申请处理结果通知",userWithdrawDeposit.getRemak(), userWithdrawDeposit.getUserMail());
		result.setSuccess(true);
		return result;
	}

}
