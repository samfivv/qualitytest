package com.midai.miya.circle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.circle.model.CircleReply;
import com.midai.miya.circle.service.CircleReplyService;
import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/circleReply")
public class CircleReplyController extends BaseController {
 
	private static final long serialVersionUID = 5862959700896130714L;
	@Autowired
	private CircleReplyService circleReplyService;
	
	@RequestMapping("/findAllCircleReply")
	public @ResponseBody Grid findAllCircleReply(HttpServletRequest request,CircleReply circleReply){
		Grid grid=new Grid();
		List<CircleReply> circleReplys=circleReplyService.findByConditions(circleReply, this.getPage(request));
		long count=circleReplyService.findByConditionsCount(circleReply);
		grid.setRows(circleReplys);
		grid.setTotal(count);
		this.addLog(request, "查看全部的圈子回复", Constant.LOG_TYPE_SELECT);
		return grid;
	}
	
	@RequestMapping("/disableCircleReply")
	public @ResponseBody Result disableCircleReply(HttpServletRequest request,String mywCircleReplyId){
		Result result=new Result();
		CircleReply circleReply=new CircleReply();
		circleReply.setMywCircleReplyId(mywCircleReplyId);
		circleReply.setIsShow(2);
		circleReplyService.update(circleReply);
		result.setSuccess(true);
		result.setMsg("屏蔽成功");
		this.addLog(request, "屏蔽圈子中的回复", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	
	@RequestMapping("/openCircleReply")
	public @ResponseBody Result openCircleReply(HttpServletRequest request,String mywCircleReplyId){
		Result result=new Result();
		CircleReply circleReply=new CircleReply();
		circleReply.setMywCircleReplyId(mywCircleReplyId);
		circleReply.setIsShow(1);
		circleReplyService.update(circleReply);
		result.setSuccess(true);
		result.setMsg("取消屏蔽成功");
		this.addLog(request, "取消屏蔽圈子中的回复", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	
	@RequestMapping("/findCircleReplyImg")
	public String findCircleReplyImg(HttpServletRequest request,String mywCircleReplyId){
		CircleReply circleReply=circleReplyService.findCircleReplyById(mywCircleReplyId);
		request.setAttribute("circleReply", circleReply);
		return "securityJsp/circle/circleReplyImg";
	}

}
