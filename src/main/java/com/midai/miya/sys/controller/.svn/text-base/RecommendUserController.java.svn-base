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
import com.midai.miya.sys.model.Recommend;
import com.midai.miya.sys.model.RecommendUser;
import com.midai.miya.sys.service.RecommendUserService;
import com.midai.miya.user.model.User;
import com.midai.miya.user.service.UserService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/recommendUser")
public class RecommendUserController extends BaseController {
 
	private static final long serialVersionUID = 6491050380361600693L;
	@Autowired
	private RecommendUserService recommendUserService;
	@Autowired
	private UserService userService;
    @RequestMapping("/findAllTalent")
	public @ResponseBody Grid findAllTalent(HttpServletRequest request){
    	this.addLog(request, "查询达人推荐", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		RecommendUser recommendUser=new RecommendUser();
		recommendUser.setRecommendType(2);
		List<RecommendUser> lists=recommendUserService.findByConditions(recommendUser, this.getPage(request));
		long count=recommendUserService.findByConditionsCount(recommendUser);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}
    @RequestMapping("/findAllTalentUser")
   	public @ResponseBody Grid findAllTalentUser(User user,HttpServletRequest request){
   		Grid grid=new Grid();
   		user.setUserState(1);
   		List<User> lists=userService.getUserByCondition(user, this.getPage(request)); 
   		long count=userService.getUserCount(user);
   		grid.setRows(lists);
   		grid.setTotal(count);
   		return grid;
   	}
    
    @RequestMapping("/saveTalent")
    public @ResponseBody Result saveTalent(RecommendUser recommendUser,Integer recommendSorta,HttpServletRequest request){
    	Result result=new Result();
    	long count=recommendUserService.findByUserId(recommendUser.getUserId());
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("该达人已存在");
    	}else{
    	recommendUser.setRecommendUserId(UUIDUtil.getUUID());
    	recommendUser.setRecommendType(2);
    	recommendUser.setRecommendSort(recommendSorta);
    	recommendUserService.save(recommendUser);
    	result.setSuccess(true);
    	result.setMsg("保存成功");
    	this.addLog(request, "添加推荐达人", Constant.LOG_TYPE_ADD);
    	}
    	return result;
    }
    @RequestMapping("/updateTalent")
    public @ResponseBody Result updateTalent(RecommendUser recommendUser,Integer recommendSorta,HttpServletRequest request){
    	Result result=new Result();
    	long count=recommendUserService.findByUserId(recommendUser.getUserId());
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("该达人已存在");
    	}else{
    	recommendUser.setRecommendSort(recommendSorta);
    	recommendUserService.update(recommendUser);
    	result.setSuccess(true);
    	result.setMsg("修改成功");
    	this.addLog(request, "修改推荐达人", Constant.LOG_TYPE_UPDATE);
    	}
    	return result;
    }
    @RequestMapping("/deleteTalent")
    public @ResponseBody Result deleteTalent(String recommendUserId,HttpServletRequest request){
    	Result result=new Result();
    	RecommendUser recommendUser=new RecommendUser();
    	recommendUser.setRecommendUserId(recommendUserId);
    	recommendUserService.delete(recommendUser);
    	result.setSuccess(true);
    	result.setMsg("删除成功");
    	this.addLog(request, "删除推荐达人", Constant.LOG_TYPE_DELETE);
    	return result;
    }
    @RequestMapping("/findByTalentId")
    public @ResponseBody RecommendUser findByTalentId(String recommendUserId,HttpServletRequest request){
    	RecommendUser recommendUser=recommendUserService.findById(recommendUserId);
    	this.addLog(request, "查看达人详情", Constant.LOG_TYPE_SELECT);
    	return recommendUser;
    }
    @RequestMapping(value = "/exportRecommendTalent", method = RequestMethod.POST)
	public void exportRecommendTalent(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出达人推荐信息", Constant.LOG_TYPE_EXPORT);
		RecommendUser recommendUser=new RecommendUser();
		recommendUser.setRecommendType(2);
		List<RecommendUser> recommends=recommendUserService.findByConditions(recommendUser,this.getExportPage(request));
		super.doExport(request, response, recommends, "达人推荐", "达人推荐",
				this.getHeadForRecommendTalent(), this.getColumnForRecommendTalent());
	}
	private String[] getHeadForRecommendTalent() {
		return new String[] { "达人昵称", "个性签名", "排序"};
	}
	private String[] getColumnForRecommendTalent() {
		return new String[] { "userNickname", "userSignature", "recommendSort"};
	}
	
	@RequestMapping("/findAllOrganization")
	public @ResponseBody Grid findAllOrganization(HttpServletRequest request){
    	this.addLog(request, "查询机构推荐", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		RecommendUser recommendUser=new RecommendUser();
		recommendUser.setRecommendType(1);
		List<RecommendUser> lists=recommendUserService.findByConditions(recommendUser, this.getPage(request));
		long count=recommendUserService.findByConditionsCount(recommendUser);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}
	 @RequestMapping("/findAllOrganizationUser")
	   	public @ResponseBody Grid findAllOrganizationUser(User user,HttpServletRequest request){
	   		Grid grid=new Grid();
	   		user.setUserState(1);
	   		user.setUserType(2);
	   		List<User> lists=userService.getUserByCondition(user, this.getPage(request)); 
	   		long count=userService.getUserCount(user);
	   		grid.setRows(lists);
	   		grid.setTotal(count);
	   		return grid;
	   	}
	 @RequestMapping("/saveOrganization")
	    public @ResponseBody Result saveOrganization(RecommendUser recommendUser,Integer recommendSorta,HttpServletRequest request){
	    	Result result=new Result();
	    	long count=recommendUserService.findByUserId(recommendUser.getUserId());
	    	if(count>0){
	    		result.setSuccess(false);
	    		result.setMsg("该机构已存在");
	    	}else{
	    	recommendUser.setRecommendUserId(UUIDUtil.getUUID());
	    	recommendUser.setRecommendType(1);
	    	recommendUser.setRecommendSort(recommendSorta);
	    	recommendUserService.save(recommendUser);
	    	result.setSuccess(true);
	    	result.setMsg("保存成功");
	    	this.addLog(request, "添加推荐机构", Constant.LOG_TYPE_ADD);
	    	}
	    	return result;
	    }
	 @RequestMapping("/updateOrganization")
	    public @ResponseBody Result updateOrganization(RecommendUser recommendUser,Integer recommendSorta,HttpServletRequest request){
	    	Result result=new Result();
	    	long count=recommendUserService.findByUserId(recommendUser.getUserId());
	    	if(count>0){
	    		result.setSuccess(false);
	    		result.setMsg("该机构已存在");
	    	}else{
	    	recommendUser.setRecommendSort(recommendSorta);
	    	recommendUserService.update(recommendUser);
	    	result.setSuccess(true);
	    	result.setMsg("修改成功");
	    	this.addLog(request, "修改推荐机构", Constant.LOG_TYPE_UPDATE);
	    	}
	    	return result;
	    }
	 @RequestMapping("/deleteOrganization")
	    public @ResponseBody Result deleteOrganization(String recommendUserId,HttpServletRequest request){
	    	Result result=new Result();
	    	RecommendUser recommendUser=new RecommendUser();
	    	recommendUser.setRecommendUserId(recommendUserId);
	    	recommendUserService.delete(recommendUser);
	    	result.setSuccess(true);
	    	result.setMsg("删除成功");
	    	this.addLog(request, "删除推荐机构", Constant.LOG_TYPE_DELETE);
	    	return result;
	    }
	 @RequestMapping(value = "/exportRecommendOrganization", method = RequestMethod.POST)
		public void exportRecommendOrganization(HttpServletRequest request,
				HttpServletResponse response) throws UnsupportedEncodingException {
			this.addLog(request, "导出机构推荐信息", Constant.LOG_TYPE_EXPORT);
			RecommendUser recommendUser=new RecommendUser();
			recommendUser.setRecommendType(1);
			List<RecommendUser> recommends=recommendUserService.findByConditions(recommendUser,this.getExportPage(request));
			super.doExport(request, response, recommends, "机构推荐", "机构推荐",
					this.getHeadForRecommendOrganization(), this.getColumnForRecommendOrganization());
		}
		private String[] getHeadForRecommendOrganization() {
			return new String[] { "机构昵称", "个性签名", "排序"};
		}
		private String[] getColumnForRecommendOrganization() {
			return new String[] { "userNickname", "userSignature", "recommendSort"};
		}
}
