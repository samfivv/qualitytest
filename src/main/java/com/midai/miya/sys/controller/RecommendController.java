package com.midai.miya.sys.controller;

import java.io.UnsupportedEncodingException;
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
import com.midai.miya.sys.model.Interest;
import com.midai.miya.sys.model.Recommend;
import com.midai.miya.sys.service.CategoryService;
import com.midai.miya.sys.service.RecommendService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/RecommendController")
public class RecommendController extends BaseController{
	 
	private static final long serialVersionUID = 3496007541389508699L;
	@Autowired
	private RecommendService recommendService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/findAll")
	public @ResponseBody Grid findAll(HttpServletRequest request,Recommend recommend){
		this.addLog(request, "查询专辑", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		List<Recommend> recommends=recommendService.findByConditions(recommend, this.getPage(request));
		long count=recommendService.findByConditionsCount(recommend);
		grid.setRows(recommends);
		grid.setTotal(count);
		return grid;
		}
	@RequestMapping("/deleteRecommend")
	public @ResponseBody Result delete(String recommendId,HttpServletRequest request){
		this.addLog(request, "删除专辑", Constant.LOG_TYPE_DELETE);
		Result result=new Result();
		Recommend recommend=new Recommend();
		recommend.setRecommendId(recommendId);
		recommendService.delete(recommend);
		result.setSuccess(true);
		this.addLog(request, "删除专辑", Constant.LOG_TYPE_DELETE);
		return result;
	}
	@RequestMapping("/save")
	public @ResponseBody Result save(Recommend recommend,Integer recommendSorta,HttpServletRequest request){
		this.addLog(request, "保存专辑", Constant.LOG_TYPE_ADD);
		Result result=new Result();
		long count=recommendService.findByInterestId(recommend);
		if(count>0){
			result.setSuccess(false);
			result.setMsg("专辑已存在");
		}else{
		recommend.setRecommendId(UUIDUtil.getUUID());
		recommend.setRecommendSort(recommendSorta);
		recommendService.save(recommend);
		result.setSuccess(true);
		result.setMsg("保存成功");
		this.addLog(request, "保存专辑", Constant.LOG_TYPE_ADD);
		}
		return result;
	}
	@RequestMapping("/update")
	public @ResponseBody Result update(Recommend recommend,Integer recommendSorta,HttpServletRequest request){
		this.addLog(request, "修改专辑", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		long count=recommendService.findByInterestId(recommend);
		if(count>0){
			result.setSuccess(false);
			result.setMsg("专辑已存在");
		}else{
		recommend.setRecommendSort(recommendSorta);
		recommendService.update(recommend);
		result.setSuccess(true);
		result.setMsg("修改成功");
		this.addLog(request, "修改专辑", Constant.LOG_TYPE_UPDATE);
		}
		return result;
	}
	@RequestMapping("/findInterestById")
	public @ResponseBody Interest findInterestById(String interestId){
		Interest interest=recommendService.findInterestById(interestId);
		return interest;
	}
	@RequestMapping("/findRecommendById")
	public @ResponseBody Recommend findRecommendById(String recommendId){
		Recommend recommend=recommendService.findById(recommendId);
		return recommend;
	}
	@RequestMapping("/findInterestAll")
	public @ResponseBody Grid findInterestAll(Interest interest,HttpServletRequest request){
		Grid grid=new Grid();
		List<Interest> interests=recommendService.findInterestByConditions(interest, this.getPage(request));
		long count=recommendService.findInterestByConditionsCount(interest);
		grid.setRows(interests);
		grid.setTotal(count);
		return grid;
	}
	@RequestMapping(value = "/exportRecommend", method = RequestMethod.POST)
	public void exportCategory(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出专辑信息", Constant.LOG_TYPE_EXPORT);
		Recommend recommend=new Recommend();
		List<Recommend> recommends=recommendService.findByConditions(recommend,this.getExportPage(request));
		super.doExport(request, response, recommends, "专辑推荐", "专辑推荐",
				this.getHeadForRecommend(), this.getColumnForRecommend());
	}
	private String[] getHeadForRecommend() {
		return new String[] { "推荐ID", "兴趣ID", "排序"};
	}
	private String[] getColumnForRecommend() {
		return new String[] { "recommendId", "interestId", "recommendSort"};
	}
	
	@RequestMapping("/findCategoryNameById")
	public @ResponseBody Result findCategoryNameById(HttpServletRequest request,String categoryId){
	    Result result=new Result();
		String categoryName=categoryService.findCategoryNameByParentId(categoryId);
		result.setData(categoryName);
		result.setSuccess(true);
		return result;
	}
}
