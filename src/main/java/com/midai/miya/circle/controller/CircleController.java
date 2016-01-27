package com.midai.miya.circle.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.circle.model.Circle;
import com.midai.miya.circle.service.CircleService;
import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/circle")
public class CircleController extends BaseController {

	private static final long serialVersionUID = 622541832107279014L;
	
	@Autowired
	private CircleService circleService;
	
	@RequestMapping("/findAllCircle")
	public @ResponseBody Grid findAllCircle(HttpServletRequest request,Circle circle){
		Grid grid=new Grid();
		if(circle.getCircleState()==null){
			circle.setCircleState(3);
		}
		List<Circle> circles=circleService.findByConditions(circle, this.getPage(request));
		long count=circleService.findByConditionsCount(circle);
		grid.setRows(circles);
		grid.setTotal(count);
		this.addLog(request, "查看全部的圈子", Constant.LOG_TYPE_SELECT);
		return grid;
	}
	
	@RequestMapping("/saveCircle")
	public @ResponseBody Result saveCircle(HttpServletRequest request,Circle circle){
		Result result=new Result();
		if("".equals(circle.getCircleName().replaceAll(" ", ""))){
			result.setSuccess(false);
			result.setMsg("圈子名称不能为空");
			return result;
		}
		if("".equals(circle.getCircleDesc().replaceAll(" ", ""))){
			result.setSuccess(false);
			result.setMsg("圈子简介不能为空");
			return result;
		}
		int count=circleService.findCircleByName(circle);
		if(count>0){
			result.setSuccess(false);
			result.setMsg("圈子名称不能重复");
			return result;
		}
		circle.setCircleId(UUIDUtil.getUUID());
		circle.setCircleName(circle.getCircleName().trim());
		circle.setCircleDesc(circle.getCircleDesc().trim());
		circle.setCircleCreateTime(new Date());
		circleService.save(circle);
		result.setSuccess(true);
		result.setMsg("添加成功");
		this.addLog(request, "添加圈子", Constant.LOG_TYPE_ADD);
		return result;
	}
	
	@RequestMapping("/updateCircle")
	public @ResponseBody Result updateCircle(HttpServletRequest request,Circle circle){
		Result result=new Result();
		if(circle.getCircleName()!=null){
		if("".equals(circle.getCircleName().replaceAll(" ", ""))){
			result.setSuccess(false);
			result.setMsg("圈子名称不能为空");
			return result;
		}
		int count=circleService.findCircleByName(circle);
		if(count>0){
			result.setSuccess(false);
			result.setMsg("圈子名称不能重复");
			return result;
		}
		circle.setCircleName(circle.getCircleName().trim());
		}
		if(circle.getCircleDesc()!=null){
		if("".equals(circle.getCircleDesc().replaceAll(" ", ""))){
			result.setSuccess(false);
			result.setMsg("圈子简介不能为空");
			return result;
		}
		circle.setCircleDesc(circle.getCircleDesc().trim());
		}
		if(circle.getCircleState()==1){
			circle.setUnpassDesc("");
		}
		circleService.update(circle);
		result.setSuccess(true);
		result.setMsg("修改成功");
		this.addLog(request, "修改圈子", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	
	@RequestMapping("/deleteCircleById")
	public @ResponseBody Result deleteCircleById(HttpServletRequest request,String circleId){
		Result result=new Result();
		Circle circle=circleService.findCircleById(circleId);
		if(circle.getCircleTopicLists().size()>0){
			result.setSuccess(false);
			result.setMsg("此圈子包含话题，不能删除");
			return result;
		}
		circle.setCircleState(4);
		circleService.delete(circle);
		result.setSuccess(true);
		result.setMsg("删除成功");
		this.addLog(request, "删除圈子", Constant.LOG_TYPE_DELETE);
		return result;
	}
	
	@RequestMapping("/findCircleById")
	public @ResponseBody Circle findCircleById(HttpServletRequest request,String circleId){
		Circle circle=circleService.findCircleById(circleId);
		this.addLog(request, "查看圈子详情", Constant.LOG_TYPE_SELECT);
		return circle;
	}
	/**
	 * 推荐的圈子
	 * 王梦圆
	 * 2015年12月8日
	 */
	@RequestMapping("/findCircleByRecommend")
	public @ResponseBody Grid findCircleByRecommend(HttpServletRequest request,Circle circle){
		Grid grid=new Grid();
		List<Circle> circles=circleService.findCircleByRecommend(circle, this.getPage(request));
		long count=circleService.findCircleCountByRecommend(circle);
		grid.setRows(circles);
		grid.setTotal(count);
		this.addLog(request, "查看全部的圈子", Constant.LOG_TYPE_SELECT);
		return grid;
	}

}
