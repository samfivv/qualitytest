package com.midai.miya.sys.model;

import java.io.Serializable;

public class RolePermission implements Serializable{
	
	private static final long serialVersionUID = 2662972306280138523L;
	
	private String rolePermissionId;
	private String roleId;
	private String permissionId;
	public String getRolePermissionId() {
		return rolePermissionId;
	}
	public void setRolePermissionId(String rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	
	
	
}
