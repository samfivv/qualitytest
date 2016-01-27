package com.midai.miya.ask.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.ask.model.AskWhistleBlowing;
import com.midai.miya.ask.service.AskWhistleBlowingService;
import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.model.UserBillChargeback;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/askWhidtleBlowing")
public class AskWhidtleBlowingController extends BaseController {
 
	private static final long serialVersionUID = 5093856473593866645L;
	@Autowired
	private AskWhistleBlowingService askWhidtleBlowingService;
	/**
	 * 查询全部举报
	 * 王梦圆
	 * 2015年9月7日
	 */
	@RequestMapping("/findAskWhidtleBlowing")
	public @ResponseBody Grid findAskWhidtleBlowing(HttpServletRequest request,AskWhistleBlowing askWhistleBlowing){
		Grid grid=new Grid();
		List<AskWhistleBlowing> askWhidtleBlowings;
		if(askWhistleBlowing.getWhistleState()==null){
			askWhistleBlowing.setWhistleState(1);
		}
		if(askWhistleBlowing.getWhistleBlowingType()==null){
			askWhistleBlowing.setWhistleBlowingType(1);
		}
		if(askWhistleBlowing.getWhistleBlowingType()!=null&&askWhistleBlowing.getWhistleBlowingType()==1){
			 askWhidtleBlowings=askWhidtleBlowingService.findByConditions(askWhistleBlowing, this.getPage(request));
			 grid.setRows(askWhidtleBlowings);
		}
		if(askWhistleBlowing.getWhistleBlowingType()!=null&&askWhistleBlowing.getWhistleBlowingType()==2){
			askWhidtleBlowings=askWhidtleBlowingService.findReplyByConditions(askWhistleBlowing,this.getPage(request));
			grid.setRows(askWhidtleBlowings);
		}
		long count=askWhidtleBlowingService.findByConditionsCount(askWhistleBlowing);
		grid.setTotal(count);
		return grid;
	}
	/**
	 * 根据id查询举报
	 * 王梦圆
	 * 2015年9月7日
	 */
	@RequestMapping("/findAskWhistleBlowingById")
	public @ResponseBody AskWhistleBlowing findAskWhistleBlowingById(HttpServletRequest request,String whistleBlowing,Integer whistleBlowingType){
		AskWhistleBlowing askWhistleBlowing;
		if(whistleBlowingType==1){
			askWhistleBlowing=askWhidtleBlowingService.findAskWhistleBlowingById(whistleBlowing);
		}else{
			askWhistleBlowing=askWhidtleBlowingService.findReplyById(whistleBlowing);
		}
		return askWhistleBlowing;
	}
	/**
	 * 修改举报的阅读状态
	 * 王梦圆
	 * 2015年9月7日
	 */
	@RequestMapping("/update")
	public @ResponseBody Result update(HttpServletRequest request,String whistleBlowing){
		Result result =new Result();
		AskWhistleBlowing askWhistleBlowing=askWhidtleBlowingService.findAskWhistleBlowingById(whistleBlowing);
		if(askWhistleBlowing.getWhistleState()==1){
			askWhistleBlowing.setWhistleState(2);
			result.setMsg("已读");
		}else{
			askWhistleBlowing.setWhistleState(1);
			result.setMsg("未读");
		}
		askWhidtleBlowingService.update(askWhistleBlowing);
		result.setSuccess(true);
		this.addLog(request, "修改举报的阅读状态", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	@RequestMapping(value = "/exportAskWhistleBlowing" , method = RequestMethod.POST)
	public void exportAskWhistleBlowing(AskWhistleBlowing askWhistleBlowing,HttpServletRequest request, HttpServletResponse response){
		this.addLog(request, "举报", Constant.LOG_TYPE_EXPORT);
		List<AskWhistleBlowing> askWhidtleBlowings=new ArrayList<AskWhistleBlowing>();
		if(askWhistleBlowing.getWhistleState()==null){
			askWhistleBlowing.setWhistleState(1);
		}
		if(askWhistleBlowing.getWhistleBlowingType()==null){
			askWhistleBlowing.setWhistleBlowingType(1);
		}
		if(askWhistleBlowing.getWhistleBlowingType()!=null&&askWhistleBlowing.getWhistleBlowingType()==1){
			 askWhidtleBlowings=askWhidtleBlowingService.findByConditions(askWhistleBlowing,this.getExportPage(request));
		}
		if(askWhistleBlowing.getWhistleBlowingType()!=null&&askWhistleBlowing.getWhistleBlowingType()==2){
			askWhidtleBlowings=askWhidtleBlowingService.findReplyByConditions(askWhistleBlowing,this.getExportPage(request));
		}
		 super.doExport(request, response, askWhidtleBlowings,"举报","举报",this.getHeadForAskWhistleBlowing(), this.getColumnForAskWhistleBlowing());
	}
	private String[] getHeadForAskWhistleBlowing(){
		         return new String[]{
		        		 "举报人昵称","被举报内容","举报原因","举报时间","状态"
		};
	}
	private String[] getColumnForAskWhistleBlowing(){
		return new String[]{
				"userNickname","questionTitle","whistleBlowingContent","createTimeStr","questionStateStr"
		};
	}

}
