package com.midai.miya.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.easyui.Tree;
import com.midai.miya.sys.model.OpreatorRole;
import com.midai.miya.sys.model.Role;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.PageUtil;

public interface RoleService {
	void saveRole(Role role);
	List<Tree> findAll();
	void updateRole(Role role);
	Role findRole(String roleId);
	void deleteRole(String roleId);
	long findAllRoleCount(Role role);
	List<Role> findRoleByName(Role role,PageUtil pageUtil);
	
	void deleteOperatorRole(String operatorId);
	
	void add(OpreatorRole operatorRole);
	
	long findByNameRole(Role role);
	
	List<Tree> findOperatorRoleById(String operatorId);
	/**
	 *  修改用户角色
	 *  @param operatorId
	 *  @param ids
	 *  @return
	 */
	Result updateOperatorRoles(String operatorId, String ids);
}
