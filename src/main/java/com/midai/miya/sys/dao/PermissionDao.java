package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.Permission;
import com.midai.miya.sys.model.RolePermission;

public interface PermissionDao {
	
	public List<Permission> findPermissionByOperatorId(String operatorId);
	
	public List<Permission> findAllPermission();
	
	public List<Permission> findPermissionByRoleId(String roleId);
	
	void delete(String roleId);
	
	void add(RolePermission rolePermission);

   	 List<Permission> findPermission();

   	 void addPermission(@Param("permission")Permission permission);

   	 Permission findPermissionById(@Param("permissionId")String permissionId);

   	 void deletePermissionById(@Param("permissionId")String permissionId);


   	 void updatePermissionById(@Param("permission")Permission permission);

   	 long selectPermissionById(@Param("permissionId")String permissionId);

   	 List<Permission> selectPermissionAll();
}