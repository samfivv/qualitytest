package com.midai.miya.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.model.User;
import com.midai.miya.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
 
	private static final long serialVersionUID = -7968523323054952398L;
    @Autowired
	private UserService userService;
    
    @RequestMapping("/findAllCertUser")
    public @ResponseBody Grid findAllCertUser(User user,HttpServletRequest request){
    	this.addLog(request, "查询会员", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	user.setUserState(1);;
    	List<User> users=userService.findByConditions(user,this.getPage(request));
    	long count=userService.findByConditionsCount(user);
    	grid.setRows(users);
    	grid.setTotal(count);
    	return grid;
    }
    
    //查询待审核会员
    @RequestMapping("/findAllUnApprove")
    public @ResponseBody Grid findAllUnApprove(User user,HttpServletRequest request){
    	this.addLog(request, "查询待审核会员", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	user.setUserState(1);;
    	List<User> users=userService.findByConditions(user,this.getPage(request));
    	long count=userService.findByConditionsCount(user);
    	grid.setRows(users);
    	grid.setTotal(count);
    	return grid;
    } 
    
    @RequestMapping("/findAllUsers")
    public @ResponseBody Grid findAllUsers(User user,HttpServletRequest request){
    	this.addLog(request, "查询所有会员", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	List<User> users=userService.findByConditions(user,this.getPage(request));
    	long count=userService.findByConditionsCount(user);
    	grid.setRows(users);
    	grid.setTotal(count);
    	return grid;
    }
    

}
