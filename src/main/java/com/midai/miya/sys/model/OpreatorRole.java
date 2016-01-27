package com.midai.miya.sys.model;

import java.io.Serializable;

public class OpreatorRole implements Serializable {
	private static final long serialVersionUID = -1360343022110462996L;
	private String operatorRoleId;
	private String operatorId;
	private String roleId;
	public String getOperatorRoleId() {
		return operatorRoleId;
	}
	public void setOperatorRoleId(String operatorRoleId) {
		this.operatorRoleId = operatorRoleId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
