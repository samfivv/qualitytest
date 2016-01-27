package com.midai.miya.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.sys.model.Interest;
import com.midai.miya.sys.service.InterestService;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/interestApproval")
public class InterestApprovalController extends BaseController {
 
	private static final long serialVersionUID = -2134234932416525178L;
	@Autowired
	private InterestService interestService;
	
	@RequestMapping("/findAllInterest")
	@ResponseBody
	public Grid findAllInterest(Interest interest,HttpServletRequest request){
		Grid grid=new Grid();
		List<Interest> interests=interestService.findAllInterest(interest, this.getPage(request));
		long count=interestService.findAllInterestCount(interest);    
		grid.setRows(interests);  
		grid.setTotal(count);
		return grid;
	}
	@RequestMapping("/update")
	public @ResponseBody Result update(HttpServletRequest request,Interest interest){
		Result result=new Result();
	   interest.setApprovalUserName(this.getCurrentOperator(request).getOperatorId());
		interestService.update(interest);
		result.setSuccess(true);
		result.setMsg("修改成功");
		this.addLog(request, "专辑审核", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	@RequestMapping("/findInterestByInterestId")
	public @ResponseBody Interest findInterestByInterestId(HttpServletRequest request,String interestId){
		Interest interest=interestService.findInterestByInterestId(interestId);
		this.addLog(request, "查看专辑详情", Constant.LOG_TYPE_SELECT);
		return interest;
	}
	
	@RequestMapping("/findAllInterestByUser")
	public @ResponseBody Grid findAllInterestByUser(HttpServletRequest request,Interest interest,String interesttype){
		Grid grid=new Grid();
		Integer type;
		if(interesttype!=null && interesttype !=""){
			type=Integer.parseInt(interesttype);
		}else{
				type=Integer.parseInt(interest.getProductionInterestType());
		}
		List<Interest> interests=interestService.findAllInterestByUser(interest,type);
		long count=interestService.findAllInterestCountByUser(interest,type);
		grid.setRows(interests);
		grid.setTotal(count);
		return grid;
	}

}
