package com.midai.miya.sys.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
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
import com.midai.miya.easyui.Tree;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.model.Permission;
import com.midai.miya.sys.model.Role;
import com.midai.miya.sys.model.RolePermission;
import com.midai.miya.sys.model.SysConfig;
import com.midai.miya.sys.service.OperatorService;
import com.midai.miya.sys.service.PermissionService;
import com.midai.miya.sys.service.RoleService;
import com.midai.miya.sys.service.SysConfigService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.MD5;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/operatorController")
public class OperatorController extends BaseController {
	private static final long serialVersionUID = -6867923441801212435L;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OperatorService operatorService;

	@Autowired
	private RoleService roleService;

	/**
	 * 导出 彭坤 2015年4月28日
	 */
	@RequestMapping(value = "/exportSysoperator", method = RequestMethod.POST)
	public void exportSysoperator(Operator operator,
			HttpServletRequest request, HttpServletResponse response) {
		this.addLog(request, "导出操作员信息", Constant.LOG_TYPE_EXPORT);
		List<Operator> sysConfigList = operatorService.findOperatorByName(
				operator, this.getExportPage(request));
		super.doExport(request, response, sysConfigList, "操作员信息", "操作员信息",
				this.getHeadForSysConfig(), this.getColumnForSysConfig());
	}

	private String[] getHeadForSysConfig() {
		return new String[] { "登录名", "姓名", "邮箱", "状态","手机", "职位", "部门",
				"创建时间" };
	}

	private String[] getColumnForSysConfig() {
		return new String[] { "operatorName", "operatorRealName",
				"operatorMail", "operatorStateStr","operatorMobile", 
				"operatorPosition", "operatorDept", "operatorCreatetimeStr", };
	}

	/**
	 * Operator 登录 彭坤 2015年4月30日
	 */
	@RequestMapping("/login")
	public @ResponseBody Result login(Operator operator,
			HttpServletRequest request) {
		Result result = new Result();
		String name = operator.getOperatorName();
		String passWord = operator.getOperatorPassword();
		if (name == null || "".equals(name)) {
			result.setSuccess(false);
			result.setMsg("请输入用户名");
			return result;
		}
		if (passWord == null || "".equals(passWord)) {
			result.setSuccess(false);
			result.setMsg("请输入密码");
			return result;
		}
		Operator loginOperator = operatorService.findOperatorForLogin(name);
		if (loginOperator != null) {
			if (MD5.getMD5Str(passWord).equals(
					loginOperator.getOperatorPassword())) {
				if (loginOperator.getOperatorState().equals("1")) {
					request.getSession().setAttribute("_loginOperator",
							loginOperator);
					result.setSuccess(true);
					this.addLog(request, "登录", Constant.LOG_TYPE_LOGIN);
					return result;
				} else {
					result.setSuccess(false);
					result.setMsg("帐号异常");
					return result;
				}
			} else {
				result.setSuccess(false);
				result.setMsg("用户名或密码错误");
				return result;
			}
		} else {
			result.setSuccess(false);
			result.setMsg("用户名或密码错误");
			return result;
		}
	}

	@RequestMapping("/loginOut")
	public @ResponseBody Result loginOut(HttpServletRequest request) {
		this.addLog(request, "注销登录", Constant.LOG_TYPE_LOGOUT);
		request.getSession().invalidate();
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	/**
	 * 修改密码 王梦圆 2015年4月24日
	 */
	@RequestMapping("/updatePassword")
	public @ResponseBody Result updatePassword(String oldPwd, String newPwd,
			HttpServletRequest request) {
		this.addLog(request, "修改密码", Constant.LOG_TYPE_UPDATE);
		Operator operator = this.getCurrentOperator(request);
		Result result = new Result();
		if (operator.getOperatorPassword().equals(MD5.getMD5Str(oldPwd))) {
			operatorService.updatePwd(MD5.getMD5Str(newPwd),
					operator.getOperatorId());
			result.setMsg("修改成功");
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 根据用户加载菜单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findPermissionByOperatorId")
	public @ResponseBody List<Tree> findPermissionByOperatorId(
			HttpServletRequest request) {
		List<Tree> tree = null;
		Operator operator = this.getCurrentOperator(request);
		if (operator != null) {
			tree = permissionService.findPermissionByOperatorId(operator
					.getOperatorId());
		}
		return tree;
	}

	@RequestMapping("/findAllPermission")
	public @ResponseBody List<Tree> findAllPermission(HttpServletRequest request) {
		List<Tree> tree = permissionService.findAllPermission();
		return tree;
	}
	
	@RequestMapping("/selectPermissionAll")
	public @ResponseBody List<Tree> selectPermissionAll(HttpServletRequest request) {
		List<Tree> tree = permissionService.selectPermissionAll();
		return tree;
	}

	@RequestMapping("/findPermissionByRoleId")
	public @ResponseBody List<Tree> findPermissionByRoleId(String roleId,
			HttpServletRequest request) {
		this.addLog(request, "查看该角色权限", Constant.LOG_TYPE_SELECT);
		List<Tree> tree = permissionService.findPermissionByRoleId(roleId);
		return tree;
	}
	
	@RequestMapping("/findPermissionById")
	public @ResponseBody Permission findPermissionById(String permissionId,
			HttpServletRequest request) {
		Permission permission = permissionService.findPermissionById(permissionId);
		return permission;
	}
	
	@RequestMapping("/savePermission")
	@ResponseBody
	public Result addPermission(Permission permission,HttpServletRequest request) {
		this.addLog(request, "添加资源", Constant.LOG_TYPE_ADD);
		Result result = new Result();
		Permission permissions=permissionService.findPermissionById(permission.getPermissionId());
		if(permissions!=null){
			result.setSuccess(false);
			result.setMsg("Id已存在，不能重复");
		}else{
		permissionService.savePermission(permission);
		result.setSuccess(true);
		result.setMsg("保存成功");
		}
		return result;
	}
	
	@RequestMapping("/updatePermission")
	@ResponseBody
	public Result updatePermission(Permission permission,HttpServletRequest request) {
		this.addLog(request, "修改资源", Constant.LOG_TYPE_UPDATE);
		Result result = new Result();
		long count=permissionService.selectPermissionById(permission.getPermissionId());
		if(count>0){
			result.setSuccess(false);
			result.setMsg("Id已存在，不能重复");
		}else{
		permissionService.updatePermissionById(permission);
		result.setSuccess(true);
		result.setMsg("修改成功");
		}
		return result;
	}
	
	@RequestMapping("/deletePermission")
	@ResponseBody
	public Result deletePermission(String permissionId,HttpServletRequest request) {
		this.addLog(request, "删除资源", Constant.LOG_TYPE_DELETE);
		Result result = new Result();
		permissionService.deletePermissionById(permissionId);
		result.setSuccess(true);
		result.setMsg("删除成功");
		return result;
	}
	
	@RequestMapping("/deletePermissionByRoleId")
	@ResponseBody
	public Result deletePermissionByRoleId(String roleId,
			HttpServletRequest request) {
		permissionService.deletePermissionByRoleId(roleId);
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	@RequestMapping("/addPermission")
	@ResponseBody
	public Result addPermission(String roleId, String ids,
			HttpServletRequest request) {
		this.addLog(request, "角色授权", Constant.LOG_TYPE_ADD);
		permissionService.deletePermissionByRoleId(roleId);
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRoleId(roleId);
		String[] str = ids.split(",");
		for (int i = 0; i < str.length; i++) {
			rolePermission.setPermissionId(str[i]);
			rolePermission.setRolePermissionId(UUIDUtil.getUUID());
			permissionService.addPermission(rolePermission);
		}
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	@RequestMapping("/saveRole")
	@ResponseBody
	public Result saveRole(Role role, HttpServletRequest request) {
		this.addLog(request, "添加角色", Constant.LOG_TYPE_ADD);
		Operator operator = this.getCurrentOperator(request);
		Result result = new Result();
		if(role.getRoleName()!=null){
			role.setRoleName(role.getRoleName().replace(" ", ""));
		}
		long roles=roleService.findByNameRole(role);
		if(roles>=1){
			result.setMsg("角色名已存在");
			result.setSuccess(false);
		} else if (role.getRoleName().trim().equals("")) {
			result.setMsg("角色名不能为空");
			result.setSuccess(false);
		} else {
			role.setRoleId(UUIDUtil.getUUID());
			role.setRoleCreatorId(operator.getOperatorName());
			role.setRoleCreatetime(new Date());
			roleService.saveRole(role);
			result.setMsg("添加成功");
			result.setSuccess(true);
		}
		return result;
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public List<Tree> findAllRole(HttpServletRequest request) {
		List<Tree> trees = roleService.findAll();
		return trees;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result update(Role role, HttpServletRequest request) {
		this.addLog(request, "修改角色", Constant.LOG_TYPE_UPDATE);
		Result result = new Result();
		if(role.getRoleName()!=null){
			role.setRoleName(role.getRoleName().replace(" ", ""));
		}
		long roles=roleService.findByNameRole(role);
		if(roles>=1){
			result.setMsg("角色名已存在");
			result.setSuccess(false);
		} else if (role.getRoleName().trim().equals("")) {
			result.setMsg("角色名不能为空");
			result.setSuccess(false);
		} else {
			roleService.updateRole(role);
			result.setMsg("修改成功");
			result.setSuccess(true);
		}

		return result;
	}

	@RequestMapping("/findById")
	@ResponseBody
	public Role findRoleById(String roleId, HttpServletRequest request) {
		this.addLog(request, "查看角色信息", Constant.LOG_TYPE_SELECT);
		Role role = roleService.findRole(roleId);
		return role;
	}

	@RequestMapping("/deleteRole")
	@ResponseBody
	public Result deleteRole(String roleId, HttpServletRequest request) {
		this.addLog(request, "删除角色", Constant.LOG_TYPE_DELETE);
		roleService.deleteRole(roleId);
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	/**
	 * 角色过滤 王梦圆 2015年4月28日
	 */
	@RequestMapping("/findRoleByName")
	@ResponseBody
	public Grid findRoleByName(Role role, HttpServletRequest request) {
		List<Role> roles = roleService.findRoleByName(role,
				this.getPage(request));
		long total = roleService.findAllRoleCount(role);
		Grid grid = new Grid();
		grid.setTotal(total);
		grid.setRows(roles);
		return grid;
	}

	/**
	 * 导出角色参数 王梦圆 2015年4月28日
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/exportRole", method = RequestMethod.POST)
	public void exportRole(Role role, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出角色参数", Constant.LOG_TYPE_EXPORT);
		String roleName=role.getRoleName();
		String roleDesc=role.getRoleDesc();
		roleName=new String(roleName.getBytes("iso-8859-1"),"utf-8");
		roleDesc=new String(roleDesc.getBytes("iso-8859-1"),"utf-8");
		List<Role> roleList = roleService.findRoleByName(role,
				this.getExportPage(request));
		super.doExport(request, response, roleList, "角色设置", "角色设置",
				this.getHeadForRole(), this.getColumnForRole());
	}

	private String[] getHeadForRole() {
		return new String[] { "角色名称", "创建时间", "资源描述", "用户" };
	}

	private String[] getColumnForRole() {
		return new String[] { "roleName", "roleCreatetimeStr", "roleDesc",
				"roleCreatorId" };
	}

	@RequestMapping("/findAllOperator")
	public @ResponseBody Grid findAll(Operator operator,
			HttpServletRequest request) {
		request.setAttribute("operators", "operators");
		List<Operator> operators = operatorService.findOperatorByName(operator,
				this.getPage(request));
		long count = operatorService.findAllCount(operator);
		Grid grid = new Grid();
		grid.setRows(operators);
		grid.setTotal(count);
		return grid;
	}

	@RequestMapping("/findOperatorById")
	public @ResponseBody Operator findById(String operatorId,
			HttpServletRequest request) {
		Operator operator = operatorService.findById(operatorId);
		return operator;
	}

	@RequestMapping("/updateOperator")
	public @ResponseBody Result update(Operator operator,
			HttpServletRequest request) {
		this.addLog(request, "修改操作员", Constant.LOG_TYPE_UPDATE);
		Operator operators = operatorService.findOperatorByName(operator);
		Result result = new Result();
		if(operators!=null){
			result.setMsg("用户名已存在");
			result.setSuccess(false);
		}else{
			operatorService.update(operator);
			result.setSuccess(true);
			result.setMsg("修改成功");
		}
		return result;
	}

	/**
	 * 根据operatorId删除operator对象 彭坤 2015年4月30日
	 */
	@RequestMapping("/deleteOperator")
	public @ResponseBody Result delete(String operatorId,
			HttpServletRequest request) {
		Result result = new Result();
		Operator operator=operatorService.findById(operatorId);
		if(operator.getOperatorName().equals("admin")){
			result.setSuccess(false);
			result.setMsg("超级管理员admin不能被删除");
		}else{
			this.addLog(request, "删除操作员", Constant.LOG_TYPE_DELETE);
			operatorService.delete(operatorId);
			result.setSuccess(true);
			result.setMsg("删除成功");
		}
		return result;
	}

	@RequestMapping("/save")
	public @ResponseBody Result save(Operator operator,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.addLog(request, "添加操作员", Constant.LOG_TYPE_ADD);
		Result result = new Result();
		if(operator.getOperatorName()!=null){
			operator.setOperatorName(operator.getOperatorName().replace(" ", ""));
		}
		Operator operators = operatorService.findOperatorByName(operator);
		if(operators!=null){
			result.setMsg("用户名已存在");
			result.setSuccess(false);
		}else if(operator.getOperatorName().trim().equals("")){
			result.setMsg("用户名不能为空");
			result.setSuccess(false);
		}else{
			operator.setOperatorName(operator.getOperatorName());
			operator.setOperatorId(UUIDUtil.getUUID());
			operator.setOperatorPassword(MD5.getMD5Str("my12345"));
			operator.setOperatorCreateTime(new Date());
			operatorService.save(operator);
			result.setSuccess(true);
			result.setMsg("添加成功");
		}
		return result;
	}

	/**
	 * 根据operatorId查询该操作员所拥有的角色 彭坤 2015年4月30日
	 */
	@RequestMapping("/findRoleByOperatorId")
	public @ResponseBody List<Tree> findRoleByOperatorId(String operatorId,
			HttpServletRequest request) {
		this.addLog(request, "查看该操作员的角色", Constant.LOG_TYPE_SELECT);
		List<Tree> tree = roleService.findOperatorRoleById(operatorId);
		return tree;
	}

	/**
	 * 根据操作员的ID修改其角色 彭坤 2015年4月30日
	 */
	@RequestMapping("/updateOperatorRole")
	public @ResponseBody Result updateOperatorRole(String operatorId,
			String ids, HttpServletRequest request) {
		this.addLog(request, "修改操作员角色", Constant.LOG_TYPE_UPDATE);
		Result result = roleService.updateOperatorRoles(operatorId, ids);
		return result;
	}

}
