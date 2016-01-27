package com.midai.miya.circle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.circle.model.RecommendCircle;
import com.midai.miya.circle.service.RecommendCircleService;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/recommendCircle")
public class RecommendCircleController extends BaseController {
 
	private static final long serialVersionUID = 5953342475543363142L;
	
	@Autowired
	private RecommendCircleService recommendCircleService;
	@RequestMapping("/findAllRecommendCircle")
	public @ResponseBody Grid findAllRecommendCircle(HttpServletRequest request,RecommendCircle recommendCircle){
		Grid grid=new Grid();
		List<RecommendCircle> recommendCircles=recommendCircleService.findByConditions(recommendCircle, this.getPage(request));
		long count=recommendCircleService.findByConditionsCount(recommendCircle);
		grid.setRows(recommendCircles);
		grid.setTotal(count);
		return grid;
	}
	
	@RequestMapping("/findRecommendCircleById")
	public @ResponseBody RecommendCircle findRecommendCircleById(HttpServletRequest request,String recommendCircleId){
		RecommendCircle recommendCircle=recommendCircleService.findRecommendCircleById(recommendCircleId);
		return recommendCircle;
	}
    
	@RequestMapping("/saveRecommendCircle")
	public @ResponseBody Result saveRecommendCircle(HttpServletRequest request,RecommendCircle recommendCircle){
		Result result=new Result();
		int count=recommendCircleService.findRecommendCircleCountById(recommendCircle);
		if(count>0){
			result.setSuccess(false);
			result.setMsg("该圈子已存在");
			return result;
		}
		recommendCircle.setRecommendCircleId(UUIDUtil.getUUID());
		recommendCircleService.save(recommendCircle);
		result.setSuccess(true);
		result.setMsg("保存成功");
		return result;
	}
	
	@RequestMapping("/updateRecommendCircle")
	public @ResponseBody Result updateRecommendCircle(HttpServletRequest request,RecommendCircle recommendCircle){
		Result result=new Result();
		int count=recommendCircleService.findRecommendCircleCountById(recommendCircle);
		if(count>0){
			result.setSuccess(false);
			result.setMsg("该圈子已存在");
			return result;
		}
		recommendCircleService.update(recommendCircle);
		result.setSuccess(true);
		result.setMsg("修改成功");
		return result;
	}
	
	@RequestMapping("/deleteRecommendCircleById")
	public @ResponseBody Result deleteRecommendCircleById(String recommendCircleId){
		Result result=new Result();
		RecommendCircle recommendCircle=new RecommendCircle();
		recommendCircle.setRecommendCircleId(recommendCircleId);
		recommendCircleService.delete(recommendCircle);
		result.setSuccess(true);
		result.setMsg("删除成功");
		return result;
	}
}
