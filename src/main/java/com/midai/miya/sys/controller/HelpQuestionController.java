package com.midai.miya.sys.controller;

import java.io.UnsupportedEncodingException;
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
import com.midai.miya.sys.model.HelpQuestion;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.service.HelpQuestionService;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/help")
public class HelpQuestionController extends BaseController {
	@Autowired
	private HelpQuestionService helpQuestionSerivce;
	private static final long serialVersionUID = 5229364986897632044L;
	/**
	 * 查找全部问题
	 * 王梦圆
	 * 2015年6月3日
	 */
	@RequestMapping("/findAll")
	public @ResponseBody Grid findAll(HelpQuestion helpQuestion,HttpServletRequest request){
		Grid grid=new Grid();
		List<HelpQuestion> list=helpQuestionSerivce.findAll(helpQuestion, this.getPage(request));
		long count=helpQuestionSerivce.findCount(helpQuestion, this.getPage(request));
		grid.setRows(list);
		grid.setTotal(count);
		return grid;
	}
	/**
	 * 处理问题
	 * 王梦圆
	 * 2015年6月3日
	 */
	@RequestMapping("/update")
	public @ResponseBody Result update(HelpQuestion helpQuestion,HttpServletRequest request){
		this.addLog(request, "处理问题意见", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		Operator operator=this.getCurrentOperator(request);
		if(!helpQuestion.getHelpSuggestion().replace(" ", "").equals("")){
			helpQuestion.setHelpState(1);
			helpQuestion.setHelpDealTime(new Date());
		    helpQuestion.setHelpUserId(operator.getOperatorId());
			helpQuestionSerivce.update(helpQuestion);
			result.setSuccess(true);
			result.setMsg("处理成功");
		}else{
			result.setSuccess(false);
			result.setMsg("处理意见不能为空");
		}
		return result;
	}
	/**
	 * 根据id查看信息
	 * 王梦圆
	 * 2015年6月3日
	 */
	@RequestMapping("/findById")
	public @ResponseBody HelpQuestion findById(String helpQuestionId,HttpServletRequest request){
		HelpQuestion help=helpQuestionSerivce.findById(helpQuestionId);
		return help;
	}
	/**
	 * 导出问题咨询
	 * 王梦圆
	 * 2015年6月3日
	 */
	@RequestMapping(value = "/exportHelp" , method = RequestMethod.POST)
	public void exportHelp(HelpQuestion helpQuestion,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		this.addLog(request, "导出视频审核信息", Constant.LOG_TYPE_EXPORT);
		String help=helpQuestion.getHelpQuestion();
		String helpRegion=helpQuestion.getHelpRegion();
		String operatorName=helpQuestion.getOperatorName();
		String helpSuggestion= helpQuestion.getHelpSuggestion();
		if(help!=null){
			help=new String(help.getBytes("iso-8859-1"),"utf-8");
		}
		if(helpRegion!=null){
			helpRegion=new String(helpRegion.getBytes("iso-8859-1"),"utf-8");
		}
		if(operatorName!=null){
			operatorName=new String(operatorName.getBytes("iso-8859-1"),"utf-8");
		}
		if(helpSuggestion!=null){
			helpSuggestion=new String(helpSuggestion.getBytes("iso-8859-1"),"utf-8"); 
		}
		List<HelpQuestion> helpQuestionList = helpQuestionSerivce.findAll(helpQuestion,this.getExportPage(request));
		super.doExport(request, response, helpQuestionList,"帮助咨询","帮助咨询",this.getHeadForHelp(), this.getColumnForHelp());
	}
	private String[] getHeadForHelp(){
		return new String[]{
				"问题名称","页面网址","联系方式","所在地区","处理人","处理状态","处理意见","创建时间","处理时间"
		};
	}
	private String[] getColumnForHelp(){
		return new String[]{
				"helpQuestion","helpUrl","helpContactWay","helpRegion","operatorName","helpStateStr",
				"helpSuggestion","helpCreateTimeStr","helpDealTimeStr"
		};
	}
}
