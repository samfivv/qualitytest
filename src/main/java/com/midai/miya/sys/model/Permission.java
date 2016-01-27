package com.midai.miya.sys.model;

import java.io.Serializable;

public class Permission implements Serializable{
	private static final long serialVersionUID = -1946126390364986331L;
	private String permissionId;
	private String permissionName;
	private String permissionUrl;
	private Integer permissionType;
	private String permissionParentId;
	private Integer permissionIsshow;
	private Integer permissionSort;
	private String permissionImgUrl;
	
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionUrl() {
		return permissionUrl;
	}
	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}
	 
	public String getPermissionParentId() {
		return permissionParentId;
	}
	public void setPermissionParentId(String permissionParentId) {
		this.permissionParentId = permissionParentId;
	}
	 
	public String getPermissionImgUrl() {
		return permissionImgUrl;
	}
	public void setPermissionImgUrl(String permissionImgUrl) {
		this.permissionImgUrl = permissionImgUrl;
	}
	public Integer getPermissionType() {
		return permissionType;
	}
	public void setPermissionType(Integer permissionType) {
		this.permissionType = permissionType;
	}
	public Integer getPermissionIsshow() {
		return permissionIsshow;
	}
	public void setPermissionIsshow(Integer permissionIsshow) {
		this.permissionIsshow = permissionIsshow;
	}
	public Integer getPermissionSort() {
		return permissionSort;
	}
	public void setPermissionSort(Integer permissionSort) {
		this.permissionSort = permissionSort;
	}

}
