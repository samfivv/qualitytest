package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Role implements Serializable{

	private static final long serialVersionUID = -8584915941917875601L;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String roleId;
	private String roleName;
	private String roleDesc;
	private Date roleCreatetime;
	private String roleCreatorId;
	private String roleCreatetimeStr;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	public Date getRoleCreatetime() {
		return roleCreatetime;
	}
	public void setRoleCreatetime(Date roleCreatetime) {
		this.roleCreatetime = roleCreatetime;
	}
	public String getRoleCreatorId() {
		return roleCreatorId;
	}
	public void setRoleCreatorId(String roleCreatorId) {
		this.roleCreatorId = roleCreatorId;
	}
	public String getRoleCreatetimeStr() {
		if(roleCreatetime==null){
			return null;
		}else{
		return sdf.format(roleCreatetime);
		}
	}
	public void setRoleCreatetimeStr(String roleCreatetimeStr) {
		this.roleCreatetimeStr = roleCreatetimeStr;
	}
	
	

}
