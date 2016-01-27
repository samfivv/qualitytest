package com.midai.miya.user.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.user.model.User;
import com.midai.miya.user.model.UserDay;
import com.midai.miya.user.model.UserMoneyLog;
import com.midai.miya.user.model.UserOftenChangeInfo;
import com.midai.miya.user.model.userVo;
import com.midai.miya.user.service.UserMoneyLogService;
import com.midai.miya.user.service.UserOftenChangeInfoService;
import com.midai.miya.user.service.UserService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/userController")
public class UserController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2564600519049265436L;
	
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	private UserMoneyLogService userMoneyLogService;
	
	@RequestMapping("/showUser/{id}")
	public String showUser(@PathVariable String id, HttpServletRequest request) {
		User u = userService.getUserById(id);
		request.setAttribute("user", u);
		return "showUser";
	}
	
	@RequestMapping("/findUsers")
	@ResponseBody
	public Grid findUsers(User user,HttpServletRequest request){
		this.addLog(request, "过滤会员", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		List<User> userList=userService.getUserByCondition(user,this.getPage(request));
		long total=userService.getUserCount(user);
		grid.setRows(userList);
		grid.setTotal(total);
		return grid;
	}
	@RequestMapping("/disableUser")
	@ResponseBody
	public Result disableUser(User user,HttpServletRequest request){
		User u=userService.getUserById(user.getUserId());
		this.addLog(request, "将会员"+u.getUserMail()+"加入黑名单", Constant.LOG_TYPE_UPDATE);
		user.setUserState(2);
		userService.update(user);
		Result result=new Result();
		result.setMsg("加入黑名单成功");
		result.setSuccess(true);
		return result;
	}
	@RequestMapping("/openUser")
	@ResponseBody
	public Result openUser(User user,HttpServletRequest request){
		User u=userService.getUserById(user.getUserId());
		this.addLog(request, "开启"+u.getUserMail()+"会员", Constant.LOG_TYPE_UPDATE);
		user.setUserState(1);
		userService.update(user);
		Result result=new Result();
		result.setMsg("开启成功");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping("/setUser")
	public @ResponseBody Result setUser(User user,HttpServletRequest request){
		Result result=new Result();
		User u=userService.getUserById(user.getUserId());
		if(u.getIsInside()==0){
			this.addLog(request, "将用户"+u.getUserNickname()+"加入内部员工", Constant.LOG_TYPE_UPDATE);
			u.setIsInside(1);
			result.setMsg("加入成功");
		}else{
			this.addLog(request, "将用户"+u.getUserNickname()+"移出内部员工", Constant.LOG_TYPE_UPDATE);
			u.setIsInside(0);
			result.setMsg("移出成功");
		}
		userService.update(u);
		result.setSuccess(true);
		return result;
	}
	
	/**
	 * 新注册会员图表
	 * 王梦圆
	 * 2015年5月5日
	 */
	@RequestMapping("/findCountByDay")
	@ResponseBody
	public List<UserDay> findCountByDay(UserDay userDay){
		List<UserDay> userDayCount=null;
		Integer type=userDay.getType();
		String queryBeginRegisterTimeStr=userDay.getQueryBeginRegisterTimeStr();
		String queryEndRegisterTimeStr=userDay.getQueryEndRegisterTimeStr();
		if(type==1){
			 userDayCount=userService.findCountByDay(queryBeginRegisterTimeStr, queryEndRegisterTimeStr);
		}else if(type==2){
			 userDayCount=userService.findCountByHour(queryBeginRegisterTimeStr, queryEndRegisterTimeStr);
		}
		
		return userDayCount;
	}
	/**
	 * 查询异常用户(userLastLoginTime>7)
	 * 彭坤
	 * 2015年5月4日
	 */
	@RequestMapping("/findErrorUser")
	@ResponseBody
	public Grid findErrorUser(User user,HttpServletRequest request){
		this.addLog(request, "查询异常会员", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		List<User>errorUsersList=userService.findErrorUser(user,this.getPage(request));
		long total=userService.findCountErrorUser(user);
		grid.setRows(errorUsersList);
		grid.setTotal(total);
		return grid;
	}
	/**
	 * 导出异常用户
	 * 彭坤
	 * 2015年5月4日
	 */
	@RequestMapping(value = "/exportSyserrorUser" , method = RequestMethod.POST)
	public void exportSyserrorUser(User user,HttpServletRequest request, HttpServletResponse response){
		this.addLog(request, "导出异常会员", Constant.LOG_TYPE_EXPORT);
		List<User> errorUserList=userService.findErrorUser(user,this.getExportPage(request));
		 super.doExport(request, response, errorUserList,"异常用户信息","异常用户信息",this.getHeadForSysConfig(), this.getColumnForSysConfig());
	}
	private String[] getHeadForSysConfig(){
		         return new String[]{
		        		 "邮箱","姓名","昵称","性别","用户QQ","用户状态","邮箱状态","上次登录时间","用户注册时间"
		};
	}
	private String[] getColumnForSysConfig(){
		return new String[]{
				"userMail","userName","userNickname","userSexStr","userQq","userStateStr","userMailStateStr","userLastLoginTimeStr","userRegisterTimeStr"
		};
	}
	
	/**
	 * 导出会员信息
	 * 王梦圆
	 * 2015年5月6日
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/exportUser" , method = RequestMethod.POST)
	public void exportUser(User user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		this.addLog(request, "导出会员信息", Constant.LOG_TYPE_EXPORT);
		List<User> UserList=userService.getUserByCondition(user,this.getExportPage(request));
		 super.doExport(request, response, UserList,"会员信息","会员信息",this.getHeadForUser(), this.getColumnForUser());
	}
	private String[] getHeadForUser(){
		         return new String[]{
		        		 "邮箱","姓名","昵称","性别","用户QQ","用户状态","邮箱状态","上次登录时间","用户注册时间"
		};
	}
	private String[] getColumnForUser(){
		return new String[]{
				"userMail","userName","userNickname","userSexStr","userQq","userStateStr","userMailStateStr","userLastLoginTimeStr","userRegisterTimeStr"
		};
	}
	/**
	 * 赠送财富
	 * 王梦圆
	 * 2015年9月30日
	 */
	@RequestMapping("/giveWealth")
	@ResponseBody
	public Result giveWealth(UserMoneyLog userMoney,HttpServletRequest request){
		User user=userService.getUserById(userMoney.getUserId());
		Operator operator=this.getCurrentOperator(request);
		Result result=new Result();
		userMoney.setUserMoneyLogId(UUIDUtil.getUUID());
		userMoney.setLogType(1);
		userMoney.setCreateTime(new Date());
		userMoneyLogService.save(userMoney);
		this.addLog(request, operator.getOperatorName()+"赠送了"+user.getUserNickname()+
				userMoney.getLogMoney()+"点财富,理由是"+userMoney.getLogDesc(), Constant.LOG_TYPE_SELECT);
		result.setMsg("赠送成功");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping("/getUsers")
	public String getUsers(HttpServletRequest request,userVo userVo){
		String str="";
		if(userVo.getUserMail()!=null){
			List<String> list=userVo.getUserMail();
			for(int i=0;i<list.size();i++){
				if(i==list.size()-1){
				str=str+list.get(i)+"";
				}else{
				str=list.get(i)+";";
				}
			}
		}
		request.setAttribute("userLists", str);
		return "securityJsp/user/sendMailForm";
	}
	
	@RequestMapping("/getUserPhone")
	public String getUserPhone(HttpServletRequest request,userVo userVo){
		String str="";
		if(userVo.getUserPhone()!=null){
			List<String> list=userVo.getUserPhone();
			for(int i=0;i<list.size();i++){
				if(i==list.size()-1){
				str=str+list.get(i)+"";
				}else{
				str=list.get(i)+",";
				}
			}
		}
		request.setAttribute("userPhone", str);
		return "securityJsp/user/sendMessageForm";
	}
	
	@RequestMapping("/sendMailtoUser")
	public @ResponseBody Result sendMailtoUser(HttpServletRequest request,User users){
		Result result=new Result();
		List<String> userLists=new ArrayList<String>();
		if(users.getContent()==null || "".equals(users.getContent().replaceAll(" ", ""))){
			result.setSuccess(false);
			result.setMsg("邮件内容不能为空");
			return result;
		}
		if(users.getCheckState()==1){
		String	userList=users.getUserLists();
		if(userList==null||"".equals(userList)){
			result.setSuccess(false);
			result.setMsg("没有选中用户");
			return result;
		}else{
			if(userList.contains(";")){
				String[] userMails=userList.split(";");
				for(String mail:userMails){
					userLists.add(mail);
				}
			}else{
				userLists.add(userList);
			}
		}
		}else if(users.getCheckState()==2){
			User user=new User();
			PageUtil page=new PageUtil();
			int count=userService.getUserCount(user);
			page.setRows(count);
			List<User> user1=userService.getUserByCondition(user, page);
			for(User user2:user1){
				userLists.add(user2.getUserMail());
			}	
		}else if(users.getCheckState()==3){
			String userMail=users.getUserMail();
			if(userMail.contains(";")){
				String[] userMails=userMail.split(";");
				for(String mail:userMails){
					userLists.add(mail);
				}
			}else{
				userLists.add(userMail);
			}
		}
		for(String mail:userLists){
			sendMail(users.getUserMailTitle(), users.getContent(),mail);
		}	
		result.setSuccess(true);
		result.setMsg("发送成功");
		if(users.getCheckState()==1){
			this.addLog(request, "给部分用户发送了邮件", Constant.LOG_TYPE_SELECT);
		}else if(users.getCheckState()==2){
			this.addLog(request, "给全部的用户发送了邮件", Constant.LOG_TYPE_SELECT);
		}else if(users.getCheckState()==3){
			this.addLog(request, "给自己填写的用户发送了邮件", Constant.LOG_TYPE_SELECT);
		}
		return result;
	}
	
	@RequestMapping("/sendMessageToUser")
	public @ResponseBody Result sendMessageToUser(HttpServletRequest request,User users){
		Result result=new Result();
		String userPhoneLists=users.getUserPhoneLists();
		if(users.getContent()==null||"".equals(users.getContent().replaceAll(" ", ""))){
			result.setSuccess(false);
			result.setMsg("短信内容不能为空");
			return result;
		}
		if(users.getCheckState()==1){
			if(userPhoneLists==null||"".equals(userPhoneLists)){
				result.setSuccess(false);
				result.setMsg("没有选中用户");
				return result;
			} 
		}else if(users.getCheckState()==2){
			User user=new User();
			PageUtil page=new PageUtil();
			int count=userService.getUserCount(user);
			page.setRows(count);
			List<User> user1=userService.getUserByCondition(user, page);
			userPhoneLists="";
			for(int i=0;i<user1.size();i++){
				if(i==user1.size()-1){
					if(user1.get(i).getUserPhone()!=null){
					userPhoneLists+=user1.get(i).getUserPhone();
					}
				}else{
					if(user1.get(i).getUserPhone()!=null){
						userPhoneLists+=user1.get(i).getUserPhone()+",";
					}
				}
			}
		}else if(users.getCheckState()==3){
			userPhoneLists=users.getUserPhone();
		}
		sendMsg(request, userPhoneLists, users.getContent());
		result.setSuccess(true);
		result.setMsg("发送成功");
		if(users.getCheckState()==1){
			this.addLog(request, "给部分用户发送了短信", Constant.LOG_TYPE_SELECT);
		}else if(users.getCheckState()==2){
			this.addLog(request, "给全部的用户发送了短信", Constant.LOG_TYPE_SELECT);
		}else if(users.getCheckState()==3){
			this.addLog(request, "给自己填写的用户发送了短信", Constant.LOG_TYPE_SELECT);
		}
		return result;
	}
}
