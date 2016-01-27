package com.midai.miya.circle.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.circle.model.CircleTopic;
import com.midai.miya.circle.service.CircleTopicService;
import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/circleTopic")
public class CircleTopicController extends BaseController {
	
	private static final long serialVersionUID = 622541832107279014L;
	
	@Autowired
	private CircleTopicService circleTopicService;
	
	@RequestMapping("/findAllCircleTopic")
	public @ResponseBody Grid findAllCircleTopic(HttpServletRequest request,CircleTopic circleTopic){
		Grid grid=new Grid();
		if(circleTopic.getIsShow()==null){
			circleTopic.setIsShow(1);
		} 
		List<CircleTopic> lists=circleTopicService.findByConditions(circleTopic,this.getPage(request));
		long count=circleTopicService.findByConditionsCount(circleTopic);
		grid.setRows(lists);
		grid.setTotal(count);
		this.addLog(request, "查看全部的圈子话题", Constant.LOG_TYPE_SELECT);
		return grid;
	}
	
	@RequestMapping("/disableCircleTopic")
	public @ResponseBody Result disableCircleTopic(HttpServletRequest request,String circleTopicId){
		Result result=new Result(); 
		CircleTopic circleTopic=new CircleTopic();
		circleTopic.setCircleTopicId(circleTopicId);
		circleTopic.setIsShow(2);
		circleTopicService.update(circleTopic);
		result.setSuccess(true);
		result.setMsg("屏蔽成功");
		this.addLog(request, "屏蔽圈子中的话题", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	
	@RequestMapping("/openCircleTopic")
	public @ResponseBody Result openCircleTopic(HttpServletRequest request,String circleTopicId){
		Result result=new Result(); 
		CircleTopic circleTopic=new CircleTopic();
		circleTopic.setCircleTopicId(circleTopicId);
		circleTopic.setIsShow(1);
		circleTopicService.update(circleTopic);
		result.setSuccess(true);
		result.setMsg("取消屏蔽成功");
		this.addLog(request, "取消屏蔽圈子中的话题", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	
	@RequestMapping("/topTopic")
	public @ResponseBody Result topTopic(HttpServletRequest request,String circleTopicId,Integer isTop){
		Result result=new Result(); 
		CircleTopic circleTopic=new CircleTopic();
		circleTopic.setCircleTopicId(circleTopicId);
		if(isTop==1){
			circleTopic.setIsTop(2);
			circleTopic.setToTopTime(new Date());
			result.setMsg("置顶成功");
			this.addLog(request, "置顶圈子中的话题", Constant.LOG_TYPE_UPDATE);
		}else if(isTop==2){
			circleTopic.setIsTop(1);
			circleTopic.setToTopTime(new Date());
			result.setMsg("取消置顶成功");
			this.addLog(request, "取消置顶圈子中的话题", Constant.LOG_TYPE_UPDATE);
		}
		circleTopicService.update(circleTopic);
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping("/findCircleTopicById")
	public String findCircleTopicById(HttpServletRequest request,String circleTopicId){
		CircleTopic circleTopic=circleTopicService.findCircleTopicById(circleTopicId);
		request.setAttribute("circleTopic", circleTopic);
		return "securityJsp/circle/circleTopicImg";
	}

}
