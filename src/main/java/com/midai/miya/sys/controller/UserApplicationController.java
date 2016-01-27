package com.midai.miya.sys.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.midai.miya.filter.PropertieUtil;
import com.midai.miya.sys.model.Approval;
import com.midai.miya.sys.model.OrganizationApplication;
import com.midai.miya.sys.model.UserApplication;
import com.midai.miya.sys.service.OrganizationApplicationService;
import com.midai.miya.sys.service.UserApplicationService;
import com.midai.miya.user.model.User;
import com.midai.miya.user.service.UserService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UserunPassReasonMap;
@Controller
@RequestMapping("/userApplication")
public class UserApplicationController extends BaseController {

	private static final long serialVersionUID = -6248216086207960497L;
	@Autowired
	private UserApplicationService userApplicationService;
	@Autowired
	private OrganizationApplicationService organizationApplicationService;
	
	@RequestMapping("/findByConditions")
	public @ResponseBody Grid findByConditions(HttpServletRequest request,UserApplication userApplication){
		this.addLog(request, "查询特长人信息", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		List<UserApplication> lists=userApplicationService.findByConditions(userApplication, this.getPage(request));
		long count=userApplicationService.findByConditionsCount(userApplication);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}
	
	@RequestMapping("/findById")
	public @ResponseBody UserApplication findById(String applicationId){
		UserApplication userApplication=userApplicationService.findById(applicationId);
		Map<String, String> maps=PropertieUtil.userunPassReasonMap;
		for(String key:maps.keySet()){
			if(key!=null&&!"".equals(key)){
				if(maps.get(key)!=null){
					if(userApplication!=null&&maps.get(key).equals(userApplication.getApplicationNotPassReason())){
						userApplication.setNotPassReasonState(key);
						break;
					}else{
						userApplication.setNotPassReasonState("zzz");
					}
				}
			}
		}
		return userApplication;
	}
	
	@RequestMapping("/update")
	public @ResponseBody Result update(HttpServletRequest request,UserApplication userApplication){
		this.addLog(request, "审批特长人", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		userApplication.setApplicationApproveTime(new Date());
		userApplicationService.update(userApplication);
		result.setSuccess(true);
		result.setMsg("审批成功");
		return result;
	} 
	/**
	 * 给审核不通过的原因加一个下拉框
	 * 王梦圆
	 * 2015年7月22日
	 */
	@RequestMapping("/findNotPassReason")
	public @ResponseBody List<UserunPassReasonMap> findNotPassReason(){
		List<UserunPassReasonMap> maps=new ArrayList<UserunPassReasonMap>();
		Map<String,String> map=PropertieUtil.userunPassReasonMap;
		 for(String key:map.keySet()){
			 if(key!=null&&!"".equals(key)){
				 if(map.get(key)!=null){
					 UserunPassReasonMap userunPassReasonMap=new UserunPassReasonMap();
					 userunPassReasonMap.setKey(key);
					 userunPassReasonMap.setValue(map.get(key));
					 maps.add(userunPassReasonMap);
				 }
			 }
			
		 }
		return maps;
	}
	/**
	 * 导出审批特长人信息
	 * 王梦圆
	 * 2015年7月24日
	 */
	@RequestMapping(value = "/exportUserApplication" , method = RequestMethod.POST)
	public void exportUserApplication(UserApplication userApplication,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		this.addLog(request, "导出审批特长人信息", Constant.LOG_TYPE_EXPORT);
		List<UserApplication> lists=userApplicationService.findByConditions(userApplication, this.getExportPage(request));
		super.doExport(request, response, lists,"审批特长人","审批特长人",this.getHeadForUserApplication(), this.getColumnForUserApplication());
	}
	private String[] getHeadForUserApplication(){
		return new String[]{
				"用户ID","姓名","个人简介","认证领域","电话号码","身份证号码","身份证到期时间","创建时间","审批状态","审批时间","不通过的原因"
		};
	}
	private String[] getColumnForUserApplication(){
		return new String[]{
				"userId","applicationName","applicationResume","categoryIdStr","applicationPhoneNumber","applicationIdentityCard","applicationDueTimeStr","applicationCreateTimeStr",
				"applicationStateStr","applicationApproveTimeStr","applicationNotPassReason"
		};
	}
	/**
	 * 查询所有机构申请
	 * 王梦圆
	 * 2015年8月25日
	 */
	@RequestMapping("/findOrganization")
	public @ResponseBody Grid findOrganization(HttpServletRequest request,OrganizationApplication organizationApplication){
		this.addLog(request, "查询机构信息", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		List<OrganizationApplication> lists=organizationApplicationService.findByConditions(organizationApplication, this.getPage(request));
		long count=organizationApplicationService.findByConditionsCount(organizationApplication);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}
	/**
	 * 机构审批
	 * 王梦圆
	 * 2015年8月25日
	 */
	@RequestMapping("/updateOrganization")
	public @ResponseBody Result updateOrganization(HttpServletRequest request,OrganizationApplication organizationApplication){
		this.addLog(request, "审批机构", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		organizationApplication.setApplicationApproveTime(new Date());
		organizationApplicationService.update(organizationApplication);
		result.setSuccess(true);
		result.setMsg("审批成功");
		return result;
	} 
	/**
	 * 根据id查看机构信息
	 * 王梦圆
	 * 2015年8月25日
	 */
	@RequestMapping("/findOrganizationById")
	public @ResponseBody OrganizationApplication findOrganizationById(String organizationApplicationId){
		OrganizationApplication organizationApplication=organizationApplicationService.findById(organizationApplicationId);
		Map<String, String> maps=PropertieUtil.userunPassReasonMap;
		for(String key:maps.keySet()){
			if(key!=null&&!"".equals(key)){
				if(maps.get(key)!=null){
					if(organizationApplication!=null&&maps.get(key).equals(organizationApplication.getApplicationNotPassReason())){
						organizationApplication.setNotPassReasonState(key);
						break;
					}else{
						organizationApplication.setNotPassReasonState("zzz");
					}
				}
			}
		}
		return organizationApplication;
	}
	/**
	 * 导出机构
	 * 王梦圆
	 * 2015年8月25日
	 */
	@RequestMapping(value = "/exportorganizationApplication" , method = RequestMethod.POST)
	public void exportorganizationApplication(OrganizationApplication organizationApplication,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		this.addLog(request, "导出审批特长人信息", Constant.LOG_TYPE_EXPORT);
		List<OrganizationApplication> lists=organizationApplicationService.findByConditions(organizationApplication, this.getExportPage(request));
		super.doExport(request, response, lists,"审批机构","审批机构",this.getHeadForOrganization(), this.getColumnForOrganization());
	}
	private String[] getHeadForOrganization(){
		return new String[]{
				"用户邮箱","机构全称","机构简介","认证领域","联系人","联系电话","联系邮箱","审批状态","创建时间","审批时间","不通过的原因"
		};
	}
	private String[] getColumnForOrganization(){
		return new String[]{
				"userMail","organizationName","organizationDesc","categoryIdStr","organizationLinkman","linkmanPhone","linkmanMail",
				"organizationStateStr","createTimeStr","applicationApproveTimeStr","applicationNotPassReason"
		};
	}

}
