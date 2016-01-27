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
import com.midai.miya.user.model.Club;
import com.midai.miya.user.service.ClubService;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/club")
public class ClubController extends BaseController {
 
	private static final long serialVersionUID = -8649285408819059170L;
	@Autowired
	private ClubService clubService;
	
	@RequestMapping("/findClubAll")
	public @ResponseBody Grid findClubAll(HttpServletRequest request,Club club){
		Grid grid=new Grid();
		List<Club> clubs=clubService.findByConditions(club, this.getPage(request));
		long count=clubService.findByConditionsCount(club);
		grid.setRows(clubs);
		grid.setTotal(count);
		return grid;
	}
	@RequestMapping("/save")
	public @ResponseBody Result save(HttpServletRequest request,Club club,String schoolNuma){
		Result result=new Result();
		if("".equals(club.getClubNum().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("社团编号不能为空格");
			return result;
		}
		int numcount=clubService.findClubCountByNum(club.getClubNum());
		if(numcount>0){
			result.setSuccess(false);
			result.setMsg("社团编号不能重复");
			return result;
		}
		if("".equals(club.getClubName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("社团名称不能为空格");
			return result;
		}
		int nameCount=clubService.findClubCountByName(club.getClubName());
		if(nameCount>0){
			result.setSuccess(false);
			result.setMsg("社团名称不能重复");
			return result;
		}
		club.setCreateTime(new Date());
        club.setSchoolNum(schoolNuma);
		clubService.save(club);
		result.setSuccess(true);
		result.setMsg("保存成功");
		this.addLog(request, "添加社团", Constant.LOG_TYPE_ADD);
		return result;
	}
	@RequestMapping("/update")
	public @ResponseBody Result update(HttpServletRequest request,Club club,String schoolNuma){
		Result result=new Result();
		if("".equals(club.getClubName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("社团名称不能为空格");
			return result;
		}
		int nameCount=clubService.findClubCountByNameNomine(club);
		if(nameCount>0){
			result.setSuccess(false);
			result.setMsg("社团名称不能重复");
			return result;
		}
		club.setSchoolNum(schoolNuma);
		clubService.update(club);
		result.setSuccess(true);
		result.setMsg("修改成功");
		this.addLog(request, "修改社团", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	@RequestMapping("/findClubByNum")
	public @ResponseBody Club findClubByNum(HttpServletRequest request,String clubNum){
		Club club=clubService.findClubByNum(clubNum);
		this.addLog(request, "查看社团详情", Constant.LOG_TYPE_SELECT);
		return club;
	}
	@RequestMapping("/deleteClubByNum")
	public @ResponseBody Result deleteClubByNum(HttpServletRequest request,String clubNum){
		Result result=new Result();
		Club club=new Club();
		club.setClubNum(clubNum);
		clubService.delete(club);
		result.setSuccess(true);
		result.setMsg("删除成功");
		this.addLog(request, "删除社团", Constant.LOG_TYPE_DELETE);
		return result;
	}

}
