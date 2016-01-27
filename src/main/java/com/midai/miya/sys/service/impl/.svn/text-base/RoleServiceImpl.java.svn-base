package com.midai.miya.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.easyui.Tree;
import com.midai.miya.sys.dao.RoleDao;
import com.midai.miya.sys.model.OpreatorRole;
import com.midai.miya.sys.model.Role;
import com.midai.miya.sys.service.RoleService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public void saveRole(Role role) {
		role.setRoleName(role.getRoleName().replace(" ", ""));
		roleDao.save(role);
	}

	@Override
	public List<Tree> findAll() {
		List<Role> roles=roleDao.findAllRole();
		List<Tree> trees = new ArrayList<Tree>();
		for (Role role : roles) {
			Tree node = new Tree();
			node.setText(role.getRoleName());
			node.setId(role.getRoleId());
			node.setState("open");
			//attributes.put("target", resource.getTarget());
			trees.add(node);
		}
		return trees;
	}

	@Override
	public void updateRole(Role role) {
		roleDao.update(role);
	}

	@Override
	public Role findRole(String roleId) {
		Role role=roleDao.findById(roleId);
		return role;
	}

	@Override
	public void deleteRole(String roleId) {
		roleDao.delete(roleId);
	}

	@Override
	public long findAllRoleCount(Role role) {
		if(role.getRoleName()!=null){
			role.setRoleName(role.getRoleName().replace(" ", "") );	
		}
		if(role.getRoleDesc()!=null){
			role.setRoleDesc(role.getRoleDesc().replace(" ", "") );	
		}
		return roleDao.findAllRoleCount(role);
	}

	@Override
	public List<Role> findRoleByName(Role role,PageUtil pageUtil) {
		if(role.getRoleName()!=null){
			role.setRoleName(role.getRoleName().replace(" ", "") );	
		}
		if(role.getRoleDesc()!=null){
			role.setRoleDesc(role.getRoleDesc().replace(" ", "") );	
		}
		return roleDao.findByRoleName(role,pageUtil);
	}


	@Override
	public void deleteOperatorRole(String operatorId) {
		roleDao.deleteRoleByOperatorId(operatorId);
	}

	@Override
	public void add(OpreatorRole operatorRole) {
		roleDao.addOperatorRole(operatorRole);
	}



	@Override
	public List<Tree> findOperatorRoleById(String operatorId) {
		List<Role> roles=roleDao.findOperatorRoleById(operatorId);
		List<Tree> trees = new ArrayList<Tree>();
		for (Role role : roles) {
			Tree node = new Tree();
			node.setText(role.getRoleName());
			node.setId(role.getRoleId());
			node.setState("open");
			trees.add(node);
		}
		return trees;
		 
	}

	@Override
	public Result updateOperatorRoles(String operatorId, String ids) {
		this.deleteOperatorRole(operatorId);
		OpreatorRole operatorRole = new OpreatorRole();
		String[] str = ids.split(",");
		for (int i = 0; i < str.length; i++) {
			operatorRole.setRoleId(str[i]);;
			operatorRole.setOperatorRoleId(UUIDUtil.getUUID());
			operatorRole.setOperatorId(operatorId);
			this.add(operatorRole);
		}
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("保存成功");
		return result;
	}

	@Override
	public long findByNameRole(Role role) {
		long roles=roleDao.findByNameRole(role);
		return roles;
	}

}
