package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.OpreatorRole;
import com.midai.miya.sys.model.Role;
import com.midai.miya.utils.PageUtil;

public interface RoleDao {
	
	void save(Role role);
	
	List<Role> findAllRole();
	
	void update(Role role);
	
	Role findById(String roleId);
	
	void delete(String roleId);
	
	void deleteRoleByOperatorId(String operatorId);
	
	long findAllRoleCount(@Param("role")Role role);
	
	List<Role> findByRoleName(@Param("role")Role role,@Param("page")PageUtil pageUtil);
	
	long findByNameRole(@Param("role")Role role);
	
	List<Role> findRoleByRoleId(String roleId);
	
	void deleteRole(String operatorId);
	
	void addOperatorRole(OpreatorRole operatorRole);
	
	
	List<Role> findOperatorRoleById(String operatorId);
}
