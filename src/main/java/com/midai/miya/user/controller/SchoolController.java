package com.midai.miya.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.model.School;
import com.midai.miya.user.service.SchoolService;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/school")
public class SchoolController extends BaseController {
 
	private static final long serialVersionUID = -3386527237149558723L;
	
	@Autowired
	private SchoolService schoolService;
	
	@RequestMapping("/findSchoolAll")
	public @ResponseBody Grid findSchoolAll(HttpServletRequest request,School school){
		Grid grid=new Grid();
		List<School> lists=schoolService.findByConditions(school, this.getPage(request));
		long count=schoolService.findByConditionsCount(school);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}
    @RequestMapping("/saveSchool")
	public @ResponseBody Result saveSchool(School school,HttpServletRequest request){
		Result result=new Result();
		if("".equals(school.getSchoolNum().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("学校编号不能为空格");
			return result;
		}
		int numCount=schoolService.findSchoolCountByNum(school.getSchoolNum());
		if(numCount>0){
			result.setSuccess(false);
			result.setMsg("学校编号不能重复");
			return result;
		}
		if("".equals(school.getSchoolName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("学校名称不能为空格");
			return result;
		}
		int nameCount=schoolService.findSchoolCountByName(school.getSchoolName());
		if(nameCount>0){
			result.setSuccess(false);
			result.setMsg("学校名称不能重复");
			return result;
		}
		school.setCreateTime(new Date());
		schoolService.save(school);
		result.setSuccess(true);
		result.setMsg("添加成功");
		this.addLog(request, "添加学校信息", Constant.LOG_TYPE_ADD);
		return result;
	}
    @RequestMapping("/updateSchool")
    public @ResponseBody Result updateSchool(School school,HttpServletRequest request){
    	Result result=new Result();
    	if("".equals(school.getSchoolName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("学校名称不能为空格");
			return result;
		}
    	int nameCount=schoolService.findSchoolCountByNameNomine(school);
    	if(nameCount>0){
			result.setSuccess(false);
			result.setMsg("学校名称不能重复");
			return result;
		}
    	schoolService.update(school);
    	result.setSuccess(true);
    	result.setMsg("修改成功");
    	this.addLog(request, "修改学校信息", Constant.LOG_TYPE_UPDATE);
    	return result;
    }
    @RequestMapping("/findSchoolByNum")
    public @ResponseBody School findSchoolByNum(HttpServletRequest request,String schoolNum){
    	School school=schoolService.findSchoolByNum(schoolNum);
    	this.addLog(request, "查看学校详情", Constant.LOG_TYPE_SELECT);
    	return school;
    }
    @RequestMapping("/deleteSchoolByNum")
    public @ResponseBody Result deleteSchoolByNum(HttpServletRequest request,String schoolNum){
    	Result result=new Result();
    	School school=new School();
    	school.setSchoolNum(schoolNum);
    	schoolService.delete(school);
    	result.setSuccess(true);
    	result.setMsg("删除成功");
    	this.addLog(request, "删除学校", Constant.LOG_TYPE_DELETE);
    	return result;
    }
}
