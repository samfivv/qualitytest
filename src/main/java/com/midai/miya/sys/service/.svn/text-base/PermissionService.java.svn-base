package com.midai.miya.sys.service;

import java.util.List;

import com.midai.miya.easyui.Tree;
import com.midai.miya.sys.model.Permission;
import com.midai.miya.sys.model.RolePermission;

public interface PermissionService {
	public List<Tree> findPermissionByOperatorId(String operatorId);
	
	public List<Tree> findAllPermission();
	
	public List<Tree> findPermissionByRoleId(String roleId);
	
	void deletePermissionByRoleId(String roleId);
	
	void addPermission(RolePermission rolePermission);

   	List<Permission> findPermission();

   	void savePermission(Permission permission);

   	Permission findPermissionById(String permissionId);

   	void deletePermissionById(String permissionId);


   	void updatePermissionById(Permission permission);

   	long selectPermissionById(String permissionId);

   	List<Tree> selectPermissionAll();
}